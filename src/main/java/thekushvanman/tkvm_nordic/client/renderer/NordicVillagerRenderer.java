package thekushvanman.tkvm_nordic.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import thekushvanman.tkvm_nordic.Tkvm_nordic;
import thekushvanman.tkvm_nordic.entity.NordicVillagerEntity;

public class NordicVillagerRenderer extends HumanoidMobRenderer<NordicVillagerEntity, HumanoidModel<NordicVillagerEntity>> {

    private final HumanoidModel<NordicVillagerEntity> normalModel;
    private final HumanoidModel<NordicVillagerEntity> slimModel;

    public NordicVillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
        this.normalModel = this.getModel();
        this.slimModel = new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_SLIM));
    }

    @Override
    public void render(NordicVillagerEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        this.model = entity.isFemale() ? this.slimModel : this.normalModel;
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(NordicVillagerEntity entity) {
        String profession = switch (entity.getProfession()) {
            case BLACKSMITH -> "blacksmith";
            case FARMER -> "farmer";
            case HOMESTEAD -> "homestead";
        };
        String sex = entity.isFemale() ? "female" : "male";
        int variantNumber = entity.getVariant() + 1;

        return new ResourceLocation(Tkvm_nordic.MODID,
                "textures/entity/nordic_villager/" + profession + "_" + sex + "_" + variantNumber + ".png");
    }
}