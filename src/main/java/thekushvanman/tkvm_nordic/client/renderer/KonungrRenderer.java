package thekushvanman.tkvm_nordic.client.renderer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import thekushvanman.tkvm_nordic.Tkvm_nordic;
import thekushvanman.tkvm_nordic.entity.KonungrEntity;

public class KonungrRenderer extends HumanoidMobRenderer<KonungrEntity, HumanoidModel<KonungrEntity>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/konungr.png");

    public KonungrRenderer(EntityRendererProvider.Context context) {
        // Slightly larger shadow radius to reflect boss scale; swap in a custom model
        // (e.g. a taller HumanoidModel) once you have Heimdall's dedicated geometry.
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(KonungrEntity entity) {
        return TEXTURE;
    }
}