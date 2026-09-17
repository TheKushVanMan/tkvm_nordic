package thekushvanman.tkvm_nordic.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.RegistryObject;
import thekushvanman.tkvm_nordic.init.ModItems;


public class DrengrEntity extends Monster {


    private static final RegistryObject<Item>[] WEAPON_POOL = new RegistryObject[] {
            ModItems.SPEAR,
            ModItems.DANE_AXE
    };


    public DrengrEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        RegistryObject<Item> weapon = WEAPON_POOL[this.random.nextInt(WEAPON_POOL.length)];
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(weapon.get()));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.HAUBERK_HELMET.get()));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.HAUBERK.get()));
        this.setItemSlot(EquipmentSlot.LEGS, new ItemStack(ModItems.HAUBERK_LEGGINGS.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.28D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ARMOR, 4.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 1.0D)
                .add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 0.5D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }
}