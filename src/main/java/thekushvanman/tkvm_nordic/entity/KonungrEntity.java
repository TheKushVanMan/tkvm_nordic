package thekushvanman.tkvm_nordic.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import thekushvanman.tkvm_nordic.init.ModSounds;

import net.minecraftforge.common.ForgeMod;

import thekushvanman.tkvm_nordic.init.ModItems;

public class KonungrEntity extends Monster {

    private float lastDamageAmount = 0.0F;

    public KonungrEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.HAND_AXE.get()));
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(ModItems.HAND_AXE.get()));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.GALLOWGLASS_HELMET.get()));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.GALLOWGLASS_GAMBESON.get()));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(ModItems.HAUBERK_LEGGINGS.get()));

        MobEffect stunImmunity = ForgeRegistries.MOB_EFFECTS.getValue(
                new ResourceLocation("epicfight", "stun_immunity"));
        if (stunImmunity != null) {
            this.addEffect(new MobEffectInstance(stunImmunity, -1, 0, false, false, false));
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 400.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.26D)
                .add(Attributes.ATTACK_DAMAGE, 10.0D)
                .add(Attributes.ARMOR, 8.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 3.0D)
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 0.5D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        this.lastDamageAmount = pAmount;
        return super.hurt(pSource, pAmount);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        if (lastDamageAmount >= 30.0F) {
            return ModSounds.VIKING_HIGH_PAIN.get();
        } else if (lastDamageAmount >= 20.0F) {
            return ModSounds.VIKING_MEDIUM_PAIN.get();
        } else {
            return ModSounds.VIKING_LOW_PAIN.get();
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.VIKING_DEATH.get();
    }

}