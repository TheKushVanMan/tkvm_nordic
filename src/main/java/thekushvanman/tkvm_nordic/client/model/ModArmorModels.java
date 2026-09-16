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
    public static final ModelLayerLocation HAUBERK_HELMET =
            new ModelLayerLocation(new ResourceLocation(Tkvm_nordic.MODID, "hauberk_helmet"), "main");
    public static final ModelLayerLocation WORN_HAUBERK_HELMET =
            new ModelLayerLocation(new ResourceLocation(Tkvm_nordic.MODID, "worn_hauberk_helmet"), "main");

    public static LayerDefinition createGenericOuterLayer() {
        return LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.6F), 0.0F), 64, 32);
    }

    public static LayerDefinition createGenericInnerLayer() {
        return LayerDefinition.create(HumanoidModel.createMesh(new CubeDeformation(0.5F), 0.0F), 64, 32);
    }

    public static LayerDefinition createHauberkHelmetLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(0.6F), 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
                        .texOffs(52, 59).addBox(-1.5F, -12.25F, -1.75F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 55).addBox(-3.5F, -10.25F, -3.75F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createWornHauberkHelmetLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(0.6F), 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
                        .texOffs(52, 59).addBox(-1.5F, -12.25F, -1.75F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
                        .texOffs(0, 55).addBox(-3.5F, -10.25F, -3.75F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}