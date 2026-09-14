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

}