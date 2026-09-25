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

    private static final ResourceLocation BLACKSMITH_MALE =
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/nordic_villager/blacksmith_male.png");
    private static final ResourceLocation BLACKSMITH_FEMALE =
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/nordic_villager/blacksmith_female.png");
    private static final ResourceLocation FARMER_MALE =
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/nordic_villager/farmer_male.png");
    private static final ResourceLocation FARMER_FEMALE =
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/nordic_villager/farmer_female.png");
    private static final ResourceLocation HOMESTEAD_MALE =
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/nordic_villager/homestead_male.png");
    private static final ResourceLocation HOMESTEAD_FEMALE =
            new ResourceLocation(Tkvm_nordic.MODID, "textures/entity/nordic_villager/homestead_female.png");

    // Two baked models: wide (Steve-style) arms for male, slim (Alex-style) for female.
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
        // Swap the active model before LivingEntityRenderer uses it this frame.
        this.model = entity.isFemale() ? this.slimModel : this.normalModel;
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(NordicVillagerEntity entity) {
        boolean female = entity.isFemale();
        return switch (entity.getProfession()) {
            case BLACKSMITH -> female ? BLACKSMITH_FEMALE : BLACKSMITH_MALE;
            case FARMER -> female ? FARMER_FEMALE : FARMER_MALE;
            case HOMESTEAD -> female ? HOMESTEAD_FEMALE : HOMESTEAD_MALE;
        };
    }
}