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

    public static final RegistryObject<SoundEvent> VIKING_ALL_CLEAR = SOUND_EVENTS.register("viking_all_clear",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_all_clear")));

    public static final RegistryObject<SoundEvent> VIKING_BATTLECRY_STANDING = SOUND_EVENTS.register("viking_battlecry_standing",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_battlecry_standing")));

    public static final RegistryObject<SoundEvent> VIKING_BEHIND_US = SOUND_EVENTS.register("viking_behind_us",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_behind_us")));

    public static final RegistryObject<SoundEvent> VIKING_DEATH = SOUND_EVENTS.register("viking_death",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_death")));

    public static final RegistryObject<SoundEvent> VIKING_DEFEND_ME = SOUND_EVENTS.register("viking_defend_me",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_defend_me")));

    public static final RegistryObject<SoundEvent> VIKING_FALLING_FIRE = SOUND_EVENTS.register("viking_falling_fire",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_falling_fire")));

    public static final RegistryObject<SoundEvent> VIKING_FATIGUE_GASP = SOUND_EVENTS.register("viking_fatigue_gasp",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_fatigue_gasp")));

    public static final RegistryObject<SoundEvent> VIKING_FATIGUE_GRUNT = SOUND_EVENTS.register("viking_fatigue_grunt",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_fatigue_grunt")));

    public static final RegistryObject<SoundEvent> VIKING_FIRE = SOUND_EVENTS.register("viking_fire",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_fire")));

    public static final RegistryObject<SoundEvent> VIKING_FOLLOW_ME = SOUND_EVENTS.register("viking_follow_me",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_follow_me")));

    public static final RegistryObject<SoundEvent> VIKING_FORWARD = SOUND_EVENTS.register("viking_forward",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_forward")));

    public static final RegistryObject<SoundEvent> VIKING_GOT_UR_BACK = SOUND_EVENTS.register("viking_got_ur_back",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_got_ur_back")));

    public static final RegistryObject<SoundEvent> VIKING_HELP = SOUND_EVENTS.register("viking_help",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_help")));

    public static final RegistryObject<SoundEvent> VIKING_HIGH_PAIN = SOUND_EVENTS.register("viking_high_pain",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_high_pain")));

    public static final RegistryObject<SoundEvent> VIKING_HOLD_UR_GROUND = SOUND_EVENTS.register("viking_hold_ur_ground",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_hold_ur_ground")));

    public static final RegistryObject<SoundEvent> VIKING_INCOMING = SOUND_EVENTS.register("viking_incoming",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_incoming")));

    public static final RegistryObject<SoundEvent> VIKING_LAUGH = SOUND_EVENTS.register("viking_laugh",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_laugh")));

    public static final RegistryObject<SoundEvent> VIKING_LOW_PAIN = SOUND_EVENTS.register("viking_low_pain",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_low_pain")));

    public static final RegistryObject<SoundEvent> VIKING_MEDIUM_PAIN = SOUND_EVENTS.register("viking_medium_pain",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_medium_pain")));

    public static final RegistryObject<SoundEvent> VIKING_NO = SOUND_EVENTS.register("viking_no",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_no")));

    public static final RegistryObject<SoundEvent> VIKING_RANGERS = SOUND_EVENTS.register("viking_rangers",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_rangers")));

    public static final RegistryObject<SoundEvent> VIKING_RESPECT = SOUND_EVENTS.register("viking_respect",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_respect")));

    public static final RegistryObject<SoundEvent> VIKING_RETREAT = SOUND_EVENTS.register("viking_retreat",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_retreat")));

    public static final RegistryObject<SoundEvent> VIKING_RUNNING_BATTLECRY = SOUND_EVENTS.register("viking_running_battlecry",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_running_battlecry")));

    public static final RegistryObject<SoundEvent> VIKING_SORRY = SOUND_EVENTS.register("viking_sorry",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_sorry")));

    public static final RegistryObject<SoundEvent> VIKING_TAUNT = SOUND_EVENTS.register("viking_taunt",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_taunt")));

    public static final RegistryObject<SoundEvent> VIKING_THANKS = SOUND_EVENTS.register("viking_thanks",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_thanks")));

    public static final RegistryObject<SoundEvent> VIKING_GRUNT_T1 = SOUND_EVENTS.register("viking_grunt_t1",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_grunt_t1")));

    public static final RegistryObject<SoundEvent> VIKING_GRUNT_T2 = SOUND_EVENTS.register("viking_grunt_t2",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_grunt_t2")));

    public static final RegistryObject<SoundEvent> VIKING_GRUNT_T3 = SOUND_EVENTS.register("viking_grunt_t3",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_grunt_t3")));

    public static final RegistryObject<SoundEvent> VIKING_WELCOME = SOUND_EVENTS.register("viking_welcome",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_welcome")));

    public static final RegistryObject<SoundEvent> VIKING_YES = SOUND_EVENTS.register("viking_yes",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Tkvm_nordic.MODID, "viking_yes")));

}