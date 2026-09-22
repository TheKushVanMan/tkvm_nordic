package thekushvanman.tkvm_nordic.world.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import thekushvanman.tkvm_nordic.init.ModStructures;

import java.util.Optional;

public class FlatJigsawStructure extends Structure {

    public static final Codec<FlatJigsawStructure> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(FlatJigsawStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 7).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter),
                    Codec.intRange(0, 5).fieldOf("terrain_search_radius").forGetter(structure -> structure.terrainSearchRadius),
                    Codec.intRange(0, 30).fieldOf("max_terrain_range").forGetter(structure -> structure.maxTerrainRange)
            ).apply(instance, FlatJigsawStructure::new));

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;
    private final int terrainSearchRadius;
    private final int maxTerrainRange;

    public FlatJigsawStructure(Structure.StructureSettings config,
                               Holder<StructureTemplatePool> startPool,
                               Optional<ResourceLocation> startJigsawName,
                               int size,
                               HeightProvider startHeight,
                               Optional<Heightmap.Types> projectStartToHeightmap,
                               int maxDistanceFromCenter,
                               int terrainSearchRadius,
                               int maxTerrainRange) {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.terrainSearchRadius = terrainSearchRadius;
        this.maxTerrainRange = maxTerrainRange;
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(GenerationContext context) {

        if (!StructureUtils.isRelativelyFlat(context, this.terrainSearchRadius, this.maxTerrainRange)) {
            return Optional.empty();
        }

        BlockPos startPos = new BlockPos(context.chunkPos().getMinBlockX(), 0, context.chunkPos().getMinBlockZ());

        return JigsawPlacement.addPieces(
                context,
                this.startPool,
                this.startJigsawName,
                this.size,
                startPos,
                false,
                this.projectStartToHeightmap,
                this.maxDistanceFromCenter
        );
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.FLAT_VILLAGE.get();
    }
}