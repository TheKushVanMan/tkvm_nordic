package thekushvanman.tkvm_nordic.init;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import thekushvanman.tkvm_nordic.Tkvm_nordic;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    HAUBERK("hauberk", 15, ironDefense(), 9,
            SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F,
            () -> Ingredient.of(Items.IRON_INGOT)),

    WORN_HAUBERK("worn_hauberk", 15, ironDefense(), 9,
            SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F,
            () -> Ingredient.of(Items.IRON_INGOT)),

    GALLOWGLASS("gallowglass", 5, leatherDefense(), 15,
    SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F,
            () -> Ingredient.of(Items.LEATHER));

    private static EnumMap<ArmorItem.Type, Integer> leatherDefense() {
        EnumMap<ArmorItem.Type, Integer> map = new EnumMap<>(ArmorItem.Type.class);
        map.put(ArmorItem.Type.BOOTS, 1);
        map.put(ArmorItem.Type.LEGGINGS, 2);
        map.put(ArmorItem.Type.CHESTPLATE, 3);
        map.put(ArmorItem.Type.HELMET, 1);
        return map;
    }

    private static EnumMap<ArmorItem.Type, Integer> ironDefense() {
        EnumMap<ArmorItem.Type, Integer> map = new EnumMap<>(ArmorItem.Type.class);
        map.put(ArmorItem.Type.BOOTS, 2);
        map.put(ArmorItem.Type.LEGGINGS, 5);
        map.put(ArmorItem.Type.CHESTPLATE, 6);
        map.put(ArmorItem.Type.HELMET, 2);
        return map;
    }

    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> defensePoints;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    ModArmorMaterials(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> defensePoints,
                      int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance,
                      Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.defensePoints = defensePoints;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return switch (type) {
            case BOOTS -> 13 * durabilityMultiplier;
            case LEGGINGS -> 15 * durabilityMultiplier;
            case CHESTPLATE -> 16 * durabilityMultiplier;
            case HELMET -> 11 * durabilityMultiplier;
        };
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return defensePoints.get(type);
    }

    @Override
    public int getEnchantmentValue() { return enchantmentValue; }

    @Override
    public SoundEvent getEquipSound() { return sound; }

    @Override
    public Ingredient getRepairIngredient() { return repairIngredient.get(); }

    @Override
    public String getName() { return Tkvm_nordic.MODID + ":" + name; }

    @Override
    public float getToughness() { return toughness; }

    @Override
    public float getKnockbackResistance() { return knockbackResistance; }
}