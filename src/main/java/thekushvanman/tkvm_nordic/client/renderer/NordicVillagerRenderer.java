package thekushvanman.tkvm_nordic.client.renderer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import thekushvanman.tkvm_nordic.Tkvm_nordic;
import thekushvanman.tkvm_nordic.entity.NordicVillagerEntity;

public class NordicVillagerRenderer extends HumanoidMobRenderer<NordicVillagerEntity, HumanoidModel<NordicVillagerEntity>> {

    private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/nordic_villager_1.png"),
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/nordic_villager_2.png"),
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/nordic_villager_3.png"),
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/nordic_villager_4.png"),
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/nordic_villager_5.png"),
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/nordic_villager_6.png")
    };

    public NordicVillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(NordicVillagerEntity entity) {
        int variant = Mth_clamp(entity.getVariant());
        return TEXTURES[variant];
    }

    private static int Mth_clamp(int variant) {
        return Math.max(0, Math.min(TEXTURES.length - 1, variant));
    }
}