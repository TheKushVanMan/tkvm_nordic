package thekushvanman.tkvm_nordic.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class NordicVillagerEntity extends PathfinderMob {

    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(NordicVillagerEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PROFESSION =
            SynchedEntityData.defineId(NordicVillagerEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> FEMALE =
            SynchedEntityData.defineId(NordicVillagerEntity.class, EntityDataSerializers.BOOLEAN);

    public static final int VARIANT_COUNT = 6;
    private static final int WORKSTATION_SEARCH_RADIUS = 8;
    private boolean pendingWorkstationScan = false;

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
                .add(Attributes.FOLLOW_RANGE, 16.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }
    private Profession findProfessionFromNearbyWorkstation() {
        BlockPos center = this.blockPosition();
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
        if (this.pendingWorkstationScan && this.level() instanceof ServerLevel) {
            this.setProfession(findProfessionFromNearbyWorkstation());
            this.pendingWorkstationScan = false;
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
            // Profession was already restored from the save - skip the scan entirely.
            this.pendingWorkstationScan = false;
        }
        if (tag.contains("Female")) {
            this.setFemale(tag.getBoolean("Female"));
        }
    }
}