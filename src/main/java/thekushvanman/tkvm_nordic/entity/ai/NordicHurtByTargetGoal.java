package thekushvanman.tkvm_nordic.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.phys.AABB;
import thekushvanman.tkvm_nordic.entity.NordicFaction;

import java.util.List;

public class NordicHurtByTargetGoal extends HurtByTargetGoal {

    private static final double ALERT_RADIUS = 16.0D;   // blocks, horizontally
    private static final double ALERT_HEIGHT = 8.0D;    // blocks, up and down

    public NordicHurtByTargetGoal(PathfinderMob mob) {
        super(mob, NordicFaction.class);
        this.setAlertOthers();
    }

    @Override
    protected void alertOthers() {
        LivingEntity attacker = this.mob.getLastHurtByMob();
        if (attacker == null) {
            return;
        }

        AABB area = this.mob.getBoundingBox().inflate(ALERT_RADIUS, ALERT_HEIGHT, ALERT_RADIUS);
        List<Mob> allies = this.mob.level().getEntitiesOfClass(Mob.class, area, other ->
                other != this.mob
                        && other instanceof NordicFaction
                        && other.isAlive()
                        && other.getTarget() == null
                        && !other.isAlliedTo(attacker));

        for (Mob ally : allies) {
            ally.setTarget(attacker);
        }
    }
}