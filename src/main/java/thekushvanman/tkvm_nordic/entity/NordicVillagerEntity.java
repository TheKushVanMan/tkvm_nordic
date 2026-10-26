package thekushvanman.tkvm_nordic.entity;

import thekushvanman.tkvm_nordic.entity.ai.NordicHurtByTargetGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import thekushvanman.tkvm_nordic.init.ModSounds;

public class NordicVillagerEntity extends PathfinderMob implements NordicFaction {

    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(NordicVillagerEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PROFESSION =
            SynchedEntityData.defineId(NordicVillagerEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> FEMALE =
            SynchedEntityData.defineId(NordicVillagerEntity.class, EntityDataSerializers.BOOLEAN);

    public static final int VARIANT_COUNT = 3;
    private static final int WORKSTATION_SEARCH_RADIUS = 8;
    private boolean pendingWorkstationScan = false;
    private int professionCheckCooldown = 0;

    // Greeting: a villager says an idle line once it has been looking at a nearby
    // player for a moment. Once per visit, with a cooldown so it never spams.
    private static final double GREET_RADIUS = 5.0D;   // blocks
    private static final int GREET_DELAY = 40;          // ticks the player must stay nearby (2 s)
    private static final int GREET_COOLDOWN = 600;      // ticks before this villager greets again (30 s)
    private int playerNearbyTicks = 0;
    private int greetCooldown = 0;
    private boolean greetedThisVisit = false;

    public enum Profession {
        HOMESTEAD,
        BLACKSMITH,
        FARMER;

        public static Profession byId(int id) {
            Profession[] values = values();
            return id >= 0 && id < values.length ? values[id] : HOMESTEAD;
        }
    }

    public NordicVillagerEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(PROFESSION, Profession.HOMESTEAD.ordinal());
        this.entityData.define(FEMALE, false);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public Profession getProfession() {
        return Profession.byId(this.entityData.get(PROFESSION));
    }

    public void setProfession(Profession profession) {
        this.entityData.set(PROFESSION, profession.ordinal());
    }

    public boolean isFemale() {
        return this.entityData.get(FEMALE);
    }

    public void setFemale(boolean female) {
        this.entityData.set(FEMALE, female);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.4D)
                .add(Attributes.FOLLOW_RANGE, 16.0D)   // also the radius villagers are called to help from
                .add(Attributes.ATTACK_DAMAGE, 2.0D);   // weak punch; required by MeleeAttackGoal
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        // Fights back against whatever hurt it (hits from other villagers are ignored),
        // and calls nearby Nordic villagers that aren't already fighting to join in.
        this.targetSelector.addGoal(1, new NordicHurtByTargetGoal(this));
    }

    private Profession findProfessionFromNearbyWorkstation() {
        BlockPos center = this.hasRestriction() ? this.getRestrictCenter() : this.blockPosition();
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();

        for (int dx = -WORKSTATION_SEARCH_RADIUS; dx <= WORKSTATION_SEARCH_RADIUS; dx++) {
            for (int dy = -2; dy <= 2; dy++) {
                for (int dz = -WORKSTATION_SEARCH_RADIUS; dz <= WORKSTATION_SEARCH_RADIUS; dz++) {
                    cursor.setWithOffset(center, dx, dy, dz);
                    BlockState state = this.level().getBlockState(cursor);

                    if (isAnvil(state)) {
                        return Profession.BLACKSMITH;
                    }
                    if (state.is(Blocks.COMPOSTER)) {
                        return Profession.FARMER;
                    }
                }
            }
        }
        return Profession.HOMESTEAD;
    }

    private static boolean isAnvil(BlockState state) {
        return state.is(Blocks.ANVIL) || state.is(Blocks.CHIPPED_ANVIL) || state.is(Blocks.DAMAGED_ANVIL);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnData,
                                        @Nullable CompoundTag tag) {
        this.setVariant(this.random.nextInt(VARIANT_COUNT));
        this.setFemale(this.random.nextBoolean());
        this.pendingWorkstationScan = true;

        this.restrictTo(this.blockPosition(), 10);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, tag);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        this.tickGreeting();

        // Scan on the first tick after spawning, then re-check every ~5-7 seconds while
        // still a Homestead villager. Villagers that already have a job keep it.
        boolean recheck = this.getProfession() == Profession.HOMESTEAD && --this.professionCheckCooldown <= 0;
        if (!this.pendingWorkstationScan && !recheck) {
            return;
        }
        this.pendingWorkstationScan = false;
        this.professionCheckCooldown = 100 + this.random.nextInt(40);

        Profession found = findProfessionFromNearbyWorkstation();
        if (found != this.getProfession()) {
            this.setProfession(found);
            if (found != Profession.HOMESTEAD && this.level() instanceof ServerLevel server) {
                server.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                        this.getX(), this.getY() + this.getBbHeight() + 0.2, this.getZ(),
                        8, 0.4, 0.3, 0.4, 0.0);
                this.playSound(found == Profession.BLACKSMITH
                        ? SoundEvents.VILLAGER_WORK_TOOLSMITH
                        : SoundEvents.VILLAGER_WORK_FARMER, 1.0F, 1.0F);
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Variant", this.getVariant());
        tag.putInt("Profession", this.getProfession().ordinal());
        tag.putBoolean("Female", this.isFemale());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariant(tag.getInt("Variant"));
        if (tag.contains("Profession")) {
            this.setProfession(Profession.byId(tag.getInt("Profession")));
            this.pendingWorkstationScan = false;
        }
        if (tag.contains("Female")) {
            this.setFemale(tag.getBoolean("Female"));
        }
    }

    private void tickGreeting() {
        if (this.greetCooldown > 0) {
            this.greetCooldown--;
        }

        Player player = this.level().getNearestPlayer(this, GREET_RADIUS);
        if (player == null || player.isSpectator() || this.getTarget() != null) {
            // Nobody nearby (or we're fighting): the visit is over, so the next one can be greeted.
            this.playerNearbyTicks = 0;
            this.greetedThisVisit = false;
            return;
        }

        this.playerNearbyTicks++;
        if (!this.greetedThisVisit && this.greetCooldown <= 0
                && this.playerNearbyTicks >= GREET_DELAY && this.isLookingAt(player)) {
            this.playSound(this.isFemale()
                    ? ModSounds.NORDIC_FEMALE_AMBIENT.get()
                    : ModSounds.NORDIC_MALE_AMBIENT.get(), this.getSoundVolume(), this.getVoicePitch());
            this.greetedThisVisit = true;
            this.greetCooldown = GREET_COOLDOWN;
        }
    }

    /** True if the villager's head is turned toward the player and nothing blocks the view. */
    private boolean isLookingAt(Player player) {
        Vec3 view = this.getViewVector(1.0F).normalize();
        Vec3 toPlayer = new Vec3(player.getX() - this.getX(),
                player.getEyeY() - this.getEyeY(),
                player.getZ() - this.getZ()).normalize();
        return view.dot(toPlayer) > 0.9D && this.hasLineOfSight(player);
    }

    // ---- Sounds: one entity, separate male / female audio sets ----

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return this.isFemale() ? ModSounds.NORDIC_FEMALE_HURT.get() : ModSounds.NORDIC_MALE_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return this.isFemale() ? ModSounds.NORDIC_FEMALE_DEATH.get() : ModSounds.NORDIC_MALE_DEATH.get();
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }
}
