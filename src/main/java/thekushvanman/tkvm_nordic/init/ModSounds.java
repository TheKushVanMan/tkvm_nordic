package thekushvanman.tkvm_nordic.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import thekushvanman.tkvm_nordic.Tkvm_nordic;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Tkvm_nordic.MODID);

    public static final RegistryObject<SoundEvent> KONUNGR_THEME_INTRO = SOUND_EVENTS.register("konungr_theme_intro",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "konungr_theme_intro")));

    public static final RegistryObject<SoundEvent> KONUNGR_THEME = SOUND_EVENTS.register("konungr_theme",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "konungr_theme")));
}