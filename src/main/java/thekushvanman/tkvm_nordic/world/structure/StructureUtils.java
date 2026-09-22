package thekushvanman.tkvm_nordic.world.structure;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;

public class StructureUtils {

    public static boolean isRelativelyFlat(Structure.GenerationContext context, int chunkRadius, int maxVariance) {
        ChunkPos chunkPos = context.chunkPos();
        int centerBlockX = chunkPos.getMiddleBlockX();
        int centerBlockZ = chunkPos.getMiddleBlockZ();
        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;

        for (int x = -chunkRadius; x <= chunkRadius; x++) {
            for (int z = -chunkRadius; z <= chunkRadius; z++) {
                int height = context.chunkGenerator().getBaseHeight(
                        centerBlockX + (x * 16),
                        centerBlockZ + (z * 16),
                        Heightmap.Types.WORLD_SURFACE_WG,
                        context.heightAccessor(),
                        context.randomState()
                );
                minHeight = Math.min(minHeight, height);
                maxHeight = Math.max(maxHeight, height);
            }
        }
        return (maxHeight - minHeight) <= maxVariance;
    }
}