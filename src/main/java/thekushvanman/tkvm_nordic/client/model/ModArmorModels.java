package thekushvanman.tkvm_nordic.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import thekushvanman.tkvm_nordic.Tkvm_nordic;

public class ModArmorModels {

    public static final ModelLayerLocation GENERIC_OUTER =
            new ModelLayerLocation(new ResourceLocation(Tkvm_nordic.MODID, "generic_outer"), "main");
    public static final ModelLayerLocation GENERIC_INNER =
            new ModelLayerLocation(new ResourceLocation(Tkvm_nordic.MODID, "generic_inner"), "main");
    public static final ModelLayerLocation SPANGENHELM =
            new ModelLayerLocation(new ResourceLocation(Tkvm_nordic.MODID, "spangenhelm"), "main");

    public static LayerDefinition createGenericOuterLayer() {
        return LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.6F), 0.0F), 64, 32);
    }

    public static LayerDefinition createGenericInnerLayer() {
        return LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.5F), 0.0F), 64, 32);
    }

    public static LayerDefinition createSpangenhelmLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(0.6F), 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        // Dome — sits slightly proud of the vanilla head cube
        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.55F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        // Nasal guard — thin bar dropping down the front of the face
        head.addOrReplaceChild("nasal_guard", CubeListBuilder.create()
                        .texOffs(32, 0).addBox(-0.5F, -1.0F, -5.2F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -8.0F, 0.0F));

        // Brow band — thin rim around the base of the dome (spangenhelm banding)
        head.addOrReplaceChild("brow_band", CubeListBuilder.create()
                        .texOffs(0, 20).addBox(-4.2F, -1.0F, -4.2F, 8.4F, 1.0F, 8.4F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, -8.0F, 0.0F));

        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}