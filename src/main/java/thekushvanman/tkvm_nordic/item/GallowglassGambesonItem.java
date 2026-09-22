package thekushvanman.tkvm_nordic.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import thekushvanman.tkvm_nordic.item.CustomArmorItem;

public class GallowglassGambesonItem extends CustomArmorItem implements DyeableLeatherItem {

    public GallowglassGambesonItem(ArmorMaterial material, ArmorItem.Type type, Item.Properties properties,
                                   String modelId, String textureName) {
        super(material, type, properties, modelId, textureName);
    }

    @Override
    public int getColor(ItemStack stack) {
        return hasCustomColor(stack) ? DyeableLeatherItem.super.getColor(stack) : 0xA0A0A0;
    }
}