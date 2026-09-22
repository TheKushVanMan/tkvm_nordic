package thekushvanman.tkvm_nordic.init;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import thekushvanman.tkvm_nordic.Tkvm_nordic;
import thekushvanman.tkvm_nordic.entity.*;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Tkvm_nordic.MODID);

    public static final RegistryObject<EntityType<ThrallEntity>> THRALL = ENTITY_TYPES.register("thrall",
            () -> EntityType.Builder.of(ThrallEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build("thrall"));


    public static final RegistryObject<EntityType<KriegsmanEntity>> KRIEGSMAN = ENTITY_TYPES.register("kriegsman",
            () -> EntityType.Builder.of(KriegsmanEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build("kriegsman"));


    public static final RegistryObject<EntityType<DrengrEntity>> DRENGR = ENTITY_TYPES.register("drengr",
            () -> EntityType.Builder.of(DrengrEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build("drengr"));

    public static final RegistryObject<EntityType<EinherjarEntity>> EINHERJAR = ENTITY_TYPES.register("einherjar",
            () -> EntityType.Builder.of(EinherjarEntity::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F)
                    .build("einherjar"));

    // Tier 3 - named boss unit
    public static final RegistryObject<EntityType<KonungrEntity>> KONUNGR = ENTITY_TYPES.register("konungr",
            () -> EntityType.Builder.of(KonungrEntity::new, MobCategory.MONSTER)
                    .sized(0.9F, 2.6F)
                    .fireImmune()
                    .build("konungr"));

}