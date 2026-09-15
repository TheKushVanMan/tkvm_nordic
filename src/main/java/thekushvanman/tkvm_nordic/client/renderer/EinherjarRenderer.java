package thekushvanman.tkvm_nordic.client.renderer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import thekushvanman.tkvm_nordic.Tkvm_nordic;
import thekushvanman.tkvm_nordic.entity.EinherjarEntity;

public class EinherjarRenderer extends HumanoidMobRenderer<EinherjarEntity, HumanoidModel<EinherjarEntity>> {

    private static final ResourceLocation TEXTURE =
        new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/einherjar.png");

    public EinherjarRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(EinherjarEntity entity) {return TEXTURE;}
}
