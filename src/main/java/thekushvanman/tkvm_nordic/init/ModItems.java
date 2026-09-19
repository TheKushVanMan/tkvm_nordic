package thekushvanman.tkvm_nordic.init;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.minecraft.world.item.ArmorItem;
import thekushvanman.tkvm_nordic.items.CustomArmorItem;

import thekushvanman.tkvm_nordic.Tkvm_nordic;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Tkvm_nordic.MODID);

    private static final Tier NORDIC_TIER = Tiers.IRON;

    public static final RegistryObject<Item> SPEAR = ITEMS.register("spear",
            () -> new SwordItem(NORDIC_TIER, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> SHORT_SPEAR = ITEMS.register("short_spear",
            () -> new SwordItem(NORDIC_TIER, 2, -2.0F, new Item.Properties()));

    public static final RegistryObject<Item> NORSE_SWORD = ITEMS.register("norse_sword",
            () -> new SwordItem(NORDIC_TIER, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> NORSE_LONGSWORD = ITEMS.register("norse_longsword",
            () -> new SwordItem(NORDIC_TIER, 4, -2.2F, new Item.Properties()));

    public static final RegistryObject<Item> HAND_AXE = ITEMS.register("hand_axe",
            () -> new AxeItem(NORDIC_TIER, 7.0F, -3.2F, new Item.Properties()));

    public static final RegistryObject<Item> DANE_AXE = ITEMS.register("dane_axe",
            () -> new AxeItem(NORDIC_TIER, 7.0F, -3.2F, new Item.Properties()));

    public static final RegistryObject<Item> BEARDED_AXE = ITEMS.register("bearded_axe",
            () -> new AxeItem(NORDIC_TIER, 6.0F, -3.0F, new Item.Properties()));

    public static final RegistryObject<Item> WARAXE = ITEMS.register("waraxe",
            () -> new AxeItem(NORDIC_TIER, 6.5F, -3.1F, new Item.Properties()));

    public static final RegistryObject<Item> ROUNDSHIELD = ITEMS.register("roundshield",
            () -> new ShieldItem(new Item.Properties().durability(336)));

    public static final RegistryObject<Item> HAUBERK = ITEMS.register("hauberk",
            () -> new CustomArmorItem(ModArmorMaterials.HAUBERK, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    "generic_outer", "hauberk"));

    public static final RegistryObject<Item> HAUBERK_LEGGINGS = ITEMS.register("hauberk_leggings",
            () -> new CustomArmorItem(ModArmorMaterials.HAUBERK, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    "generic_inner", "hauberk"));

    public static final RegistryObject<Item> WORN_HAUBERK = ITEMS.register("worn_hauberk",
            () -> new CustomArmorItem(ModArmorMaterials.WORN_HAUBERK, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    "generic_outer", "worn_hauberk"));

    public static final RegistryObject<Item> WORN_HAUBERK_LEGGINGS = ITEMS.register("worn_hauberk_leggings",
            () -> new CustomArmorItem(ModArmorMaterials.WORN_HAUBERK, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    "generic_inner", "worn_hauberk"));

    public static final RegistryObject<Item> GALLOWGLASS_HELMET = ITEMS.register("gallowglass_helmet",
            () -> new CustomArmorItem(ModArmorMaterials.GALLOWGLASS, ArmorItem.Type.HELMET, new Item.Properties(),
                    "hauberk_helmet", "gallowglass_helmet"));

    public static final RegistryObject<Item> GALLOWGLASS_GAMBESON = ITEMS.register("gallowglass_gambeson",
            () -> new CustomArmorItem(ModArmorMaterials.GALLOWGLASS, ArmorItem.Type.CHESTPLATE, new Item.Properties(),
                    "generic_outer", "gallowglass"));

    public static final RegistryObject<Item> GALLOWGLASS_LEGGINGS = ITEMS.register("gallowglass_leggings",
            () -> new CustomArmorItem(ModArmorMaterials.GALLOWGLASS, ArmorItem.Type.LEGGINGS, new Item.Properties(),
                    "generic_inner", "gallowglass"));

    public static final RegistryObject<Item> HAUBERK_HELMET = ITEMS.register("hauberk_helmet",
            () -> new CustomArmorItem(ModArmorMaterials.HAUBERK, ArmorItem.Type.HELMET, new Item.Properties(),
                    "hauberk_helmet", "hauberk_helmet"));

    public static final RegistryObject<Item> WORN_HAUBERK_HELMET = ITEMS.register("worn_hauberk_helmet",
            () -> new CustomArmorItem(ModArmorMaterials.HAUBERK, ArmorItem.Type.HELMET, new Item.Properties(),
                    "worn_hauberk_helmet", "worn_hauberk_helmet"));

    public static final RegistryObject<Item> NASAL_HELMET = ITEMS.register("nasal_helmet",
            () -> new CustomArmorItem(ModArmorMaterials.HAUBERK, ArmorItem.Type.HELMET, new Item.Properties(),
                    "hauberk_helmet", "nasal_helmet"));
}