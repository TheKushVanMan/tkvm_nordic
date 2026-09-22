package thekushvanman.tkvm_nordic.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import thekushvanman.tkvm_nordic.Tkvm_nordic;

import java.util.function.Consumer;

public class CustomArmorItem extends ArmorItem {
    private final String modelId;
    private final String textureName;

    public CustomArmorItem(ArmorMaterial material, Type type, Properties properties, String modelId, String textureName) {
        super(material, type, properties);
        this.modelId = modelId;
        this.textureName = textureName;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack,
                                                          EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                var modelManager = Minecraft.getInstance().getEntityModels();
                ModelLayerLocation layer = new ModelLayerLocation(new ResourceLocation(Tkvm_nordic.MODID, modelId), "main");
                HumanoidModel<?> model = new HumanoidModel<>(modelManager.bakeLayer(layer));
                if (_default != null) {
                    _default.copyPropertiesTo((HumanoidModel) model);
                }
                return model;
            }
        });
    }

    @Override
    public String getArmorTexture(ItemStack stack, net.minecraft.world.entity.Entity entity, EquipmentSlot slot, String type) {
        String layer = (slot == EquipmentSlot.LEGS ? "2" : "1");
        String suffix = (type != null ? "_" + type : "");
        return Tkvm_nordic.MODID + ":textures/models/armor/" + this.textureName + "_layer_" + layer + suffix + ".png";
    }
}