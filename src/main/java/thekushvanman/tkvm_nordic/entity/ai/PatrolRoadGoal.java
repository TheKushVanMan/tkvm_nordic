package thekushvanman.tkvm_nordic.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;

import java.util.EnumSet;

public class PatrolRoadGoal extends Goal {

    private final PathfinderMob mob;
    private final double speed;
    private final int searchRadius;
    private BlockPos targetPos;
    private int cooldown;

    public PatrolRoadGoal(PathfinderMob mob, double speed, int searchRadius) {
        this.mob = mob;
        this.speed = speed;
        this.searchRadius = searchRadius;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (this.cooldown > 0) {
            this.cooldown--;
            return false;
        }
        return findNearbyPathBlock() != null;
    }

    @Override
    public void start() {
        this.targetPos = findNearbyPathBlock();
        if (this.targetPos != null) {
            this.mob.getNavigation().moveTo(
                    this.targetPos.getX() + 0.5, this.targetPos.getY(), this.targetPos.getZ() + 0.5, this.speed);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetPos != null && !this.mob.getNavigation().isDone();
    }

    @Override
    public void stop() {
        this.targetPos = null;
        this.cooldown = 100 + this.mob.getRandom().nextInt(100); // pause 5-10s before next patrol leg
    }

    private BlockPos findNearbyPathBlock() {
        BlockPos origin = this.mob.blockPosition();
        BlockPos best = null;
        double bestDist = -1;

        for (int x = -this.searchRadius; x <= this.searchRadius; x += 3) {
            for (int z = -this.searchRadius; z <= this.searchRadius; z += 3) {
                BlockPos check = origin.offset(x, 0, z);
                BlockPos ground = this.mob.level().getHeightmapPos(
                        net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING, check);
                if (this.mob.level().getBlockState(ground.below()).is(Blocks.DIRT_PATH)) {
                    double dist = ground.distSqr(origin);
                    if (dist > 16 && (best == null || this.mob.getRandom().nextFloat() < 0.3F)) {
                        best = ground;
                        bestDist = dist;
                    }
                }
            }
        }
        return best;
    }
}