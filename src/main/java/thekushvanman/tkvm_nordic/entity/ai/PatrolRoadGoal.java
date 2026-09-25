package thekushvanman.tkvm_nordic.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Makes a mob patrol along Blocks.DIRT_PATH tiles.
 *
 * Unlike a coarse offset-grid scan, this checks the mob's immediate horizontal
 * neighbors each leg - reliable even when the road is only 1 block wide and
 * winds between buildings, which a sparse grid tends to miss entirely in a
 * dense village layout.
 */
public class PatrolRoadGoal extends Goal {

    private final PathfinderMob mob;
    private final double speed;
    private final int pauseMinTicks;
    private final int pauseMaxTicks;

    private BlockPos lastPos;
    private BlockPos targetPos;
    private int cooldown;

    public PatrolRoadGoal(PathfinderMob mob, double speed, int pauseMinTicks, int pauseMaxTicks) {
        this.mob = mob;
        this.speed = speed;
        this.pauseMinTicks = pauseMinTicks;
        this.pauseMaxTicks = Math.max(pauseMinTicks + 1, pauseMaxTicks);
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (this.cooldown > 0) {
            this.cooldown--;
            return false;
        }
        if (this.mob.getTarget() != null) {
            return false;
        }

        BlockPos next = findNextPathTile();
        if (next == null) {
            return false;
        }

        this.targetPos = next; // cache it - start() reuses this, doesn't re-search
        return true;
    }

    @Override
    public void start() {
        if (this.targetPos != null) {
            this.mob.getNavigation().moveTo(
                    this.targetPos.getX() + 0.5, this.targetPos.getY(), this.targetPos.getZ() + 0.5, this.speed);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.mob.getTarget() == null
                && this.targetPos != null
                && !this.mob.getNavigation().isDone();
    }

    @Override
    public void stop() {
        this.lastPos = this.mob.blockPosition();
        this.targetPos = null;
        this.cooldown = this.pauseMinTicks + this.mob.getRandom().nextInt(this.pauseMaxTicks - this.pauseMinTicks);
    }

     private BlockPos findNextPathTile() {
        BlockPos origin = this.mob.blockPosition();
        List<BlockPos> candidates = new ArrayList<>();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                if (dx == 0 && dz == 0) continue;

                BlockPos candidate = origin.offset(dx, 0, dz);
                if (candidate.equals(this.lastPos)) continue;
                if (isOnPath(candidate)) candidates.add(candidate);
            }
        }

        if (candidates.isEmpty() && this.lastPos != null && isOnPath(this.lastPos)) {
            return this.lastPos; // dead end - allow backtracking
        }
        if (candidates.isEmpty()) {
            return null;
        }

        return candidates.get(this.mob.getRandom().nextInt(candidates.size()));
    }

    private boolean isOnPath(BlockPos pos) {
        BlockState below = this.mob.level().getBlockState(pos.below());
        if (!below.is(Blocks.DIRT_PATH)) {
            return false;
        }
        return this.mob.level().getBlockState(pos).getCollisionShape(this.mob.level(), pos).isEmpty()
                && this.mob.level().getBlockState(pos.above()).getCollisionShape(this.mob.level(), pos.above()).isEmpty();
    }
}