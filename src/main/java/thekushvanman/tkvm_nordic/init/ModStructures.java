package thekushvanman.tkvm_nordic.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import thekushvanman.tkvm_nordic.Tkvm_nordic;
import thekushvanman.tkvm_nordic.world.structure.FlatJigsawStructure;

public class ModStructures {

    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, Tkvm_nordic.MODID);

    public static final RegistryObject<StructureType<FlatJigsawStructure>> FLAT_VILLAGE =
            STRUCTURE_TYPES.register("flat_village", () -> () -> FlatJigsawStructure.CODEC);
}