package thekushvanman.tkvm_nordic.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import thekushvanman.tkvm_nordic.Tkvm_nordic;
import thekushvanman.tkvm_nordic.entity.KonungrEntity;

@Mod.EventBusSubscriber(modid = Tkvm_nordic.MODID, value = Dist.CLIENT)
public class KonungrMusicTrigger {

    private static final double DISENGAGE_RANGE = 32.0D;
    private static KonungrEntity engagedKonungr = null;

    @SubscribeEvent
    public static void onTargetChange(LivingChangeTargetEvent event) {
        if (!(event.getEntity() instanceof KonungrEntity konungr)) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (event.getNewTarget() != null
                && event.getNewTarget().getId() == mc.player.getId()
                && engagedKonungr == null) {
            engagedKonungr = konungr;
            KonungrMusicManager.startEncounter();
        }
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        KonungrMusicManager.tick(); // drives the intro -> loop handoff

        if (engagedKonungr == null) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            endEncounter();
            return;
        }

        boolean konungrGone = engagedKonungr.isRemoved() || !engagedKonungr.isAlive();
        boolean outOfRange = !konungrGone
                && mc.player.distanceTo(engagedKonungr) > DISENGAGE_RANGE;

        if (konungrGone || outOfRange) {
            endEncounter();
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (event.getEntity().getId() == mc.player.getId()) {
            endEncounter();
        }
    }

    private static void endEncounter() {
        engagedKonungr = null;
        KonungrMusicManager.stopEncounter();
    }
}