package thekushvanman.tkvm_nordic.client.renderer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import thekushvanman.tkvm_nordic.Tkvm_nordic;
import thekushvanman.tkvm_nordic.entity.KriegsmanEntity;

public class KriegsmanRenderer extends HumanoidMobRenderer<KriegsmanEntity, HumanoidModel<KriegsmanEntity>> {

    private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/kriegsman.png"),
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/kriegsman_1.png"),
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/kriegsman_2.png")
    };

    public KriegsmanRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
        this.addLayer(new HumanoidArmorLayer<>(
                this,
                new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)),
                new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)),
                context.getModelManager()
        ));
    }

    @Override
    public ResourceLocation getTextureLocation(KriegsmanEntity entity) {
        int variant = entity.getVariant();
        if (variant < 0 || variant >= TEXTURES.length) {
            variant = 0;
        }
        return TEXTURES[variant];
    }
}