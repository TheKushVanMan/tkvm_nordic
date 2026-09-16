package thekushvanman.tkvm_nordic.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import thekushvanman.tkvm_nordic.client.model.ModArmorModels;
import thekushvanman.tkvm_nordic.client.renderer.EinherjarRenderer;
import thekushvanman.tkvm_nordic.init.ModEntities;
import thekushvanman.tkvm_nordic.Tkvm_nordic;
import thekushvanman.tkvm_nordic.client.renderer.DrengrRenderer;
import thekushvanman.tkvm_nordic.client.renderer.KonungrRenderer;
import thekushvanman.tkvm_nordic.client.renderer.KriegsmanRenderer;
import thekushvanman.tkvm_nordic.entity.DrengrEntity;
import thekushvanman.tkvm_nordic.entity.KonungrEntity;
import thekushvanman.tkvm_nordic.entity.KriegsmanEntity;

@Mod.EventBusSubscriber(modid = Tkvm_nordic.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NordicClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.KRIEGSMAN.get(), KriegsmanRenderer::new);
        event.registerEntityRenderer(ModEntities.DRENGR.get(), DrengrRenderer::new);
        event.registerEntityRenderer(ModEntities.EINHERJAR.get(), EinherjarRenderer::new);
        event.registerEntityRenderer(ModEntities.KONUNGR.get(), KonungrRenderer::new);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.KRIEGSMAN.get(), KriegsmanEntity.createAttributes().build());
        event.put(ModEntities.DRENGR.get(), DrengrEntity.createAttributes().build());
        event.put(ModEntities.EINHERJAR.get(), DrengrEntity.createAttributes().build());
        event.put(ModEntities.KONUNGR.get(), KonungrEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModArmorModels.GENERIC_INNER, ModArmorModels::createGenericInnerLayer);
        event.registerLayerDefinition(ModArmorModels.GENERIC_OUTER, ModArmorModels::createGenericOuterLayer);
        event.registerLayerDefinition(ModArmorModels.SPANGENHELM, ModArmorModels::createSpangenhelmLayer);
    }
}