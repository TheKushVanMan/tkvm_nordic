package thekushvanman.tkvm_nordic.client;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.Minecraft;

import thekushvanman.tkvm_nordic.Tkvm_nordic;

@Mod.EventBusSubscriber(modid = Tkvm_nordic.MODID, value = Dist.CLIENT)
public class KonungrMusicManager {
    private static KonungrTrackInstance activeTrack;

    @SubscribeEvent
    public static void onPlaySound(PlaySoundEvent event) {
        if (isMusicActive() && event.getSound().getSource() == SoundSource.MUSIC) {
            String path = event.getSound().getLocation().getPath();
            if (!path.contains("konungr_theme_intro") && !path.contains("konungr_theme")) {
                event.setSound(null);
            }
        }
    }

    public static void startEncounter() {
        Minecraft mc = Minecraft.getInstance();
        if (activeTrack != null && !activeTrack.isFadingOut()) return;

        mc.getSoundManager().stop(null, SoundSource.MUSIC);

        SoundEvent intro = ForgeRegistries.SOUND_EVENTS.getValue(
                new ResourceLocation(Tkvm_nordic.MODID, "konungr_theme_intro"));
        if (intro != null) {
            activeTrack = new KonungrTrackInstance(intro, false);
            mc.getSoundManager().play(activeTrack);
        }
    }

    public static void stopEncounter() {
        if (activeTrack != null && !activeTrack.isFadingOut()) {
            activeTrack.stopTrack();
        }
        activeTrack = null;
    }

    public static void tick() {
        if (activeTrack == null) return;

        activeTrack.incrementTicksAlive();

        if (!activeTrack.isLoop() && !activeTrack.isFadingOut() && activeTrack.getTicksAlive() > 1) {
            Minecraft mc = Minecraft.getInstance();
            if (!mc.getSoundManager().isActive(activeTrack)) {
                SoundEvent theme = ForgeRegistries.SOUND_EVENTS.getValue(
                        new ResourceLocation(Tkvm_nordic.MODID, "konungr_theme"));
                if (theme != null) {
                    activeTrack = new KonungrTrackInstance(theme, true);
                    activeTrack.setVolume(1.0F); 
                    mc.getSoundManager().play(activeTrack);
                }
            }
        }
    }

    public static boolean isMusicActive() {
        return activeTrack != null && !activeTrack.isFadingOut();
    }

    private static class KonungrTrackInstance extends AbstractTickableSoundInstance {
        private boolean fadingOut = false;
        private final boolean isLoop;
        private int ticksAlive = 0;

        protected KonungrTrackInstance(SoundEvent sound, boolean isLoop) {
            super(sound, SoundSource.MUSIC, RandomSource.create());
            this.isLoop = isLoop;
            this.looping = isLoop;
            this.volume = 0.01F;
            this.relative = true;
            this.x = 0.0D;
            this.y = 0.0D;
            this.z = 0.0D;
        }

        @Override
        public void tick() {
            if (this.fadingOut) {
                this.volume -= 0.05F;
                if (this.volume <= 0) this.stop();
            } else if (this.volume < 1.0F) {
                this.volume += 0.05F;
            }
        }

        public void stopTrack() { this.fadingOut = true; }
        public boolean isFadingOut() { return fadingOut; }
        public boolean isLoop() { return isLoop; }
        public void incrementTicksAlive() { ticksAlive++; }
        public int getTicksAlive() { return ticksAlive; }
        public void setVolume(float v) { this.volume = v; }
    }
}