package thekushvanman.tkvm_nordic.client.renderer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import thekushvanman.tkvm_nordic.Tkvm_nordic;
import thekushvanman.tkvm_nordic.entity.KriegsmanEntity;

public class KriegsmanRenderer extends HumanoidMobRenderer<KriegsmanEntity, HumanoidModel<KriegsmanEntity>> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/kriegsman.png");

    public KriegsmanRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(KriegsmanEntity entity) {
        return TEXTURE;
    }
}