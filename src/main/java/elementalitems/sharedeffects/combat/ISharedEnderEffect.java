package elementalitems.sharedeffects.combat;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public interface ISharedEnderEffect {
	default void teleportEntity(EntityLivingBase target) {
		Random rng = target.getRNG();
		// get the target's position
		BlockPos targetPosition = target.getPosition();
		double x = targetPosition.getX();
		double y = targetPosition.getY();
		double z = targetPosition.getZ();
		// get the seed for +/- x & z teleports
		List<Boolean> seed = new ArrayList<>(Arrays.asList(rng.nextInt(2) == 0, rng.nextInt(20) % 2 == 0));
		Integer maxTeleportDistance = 5;
		double newX = x + rng.nextDouble() * (maxTeleportDistance * (seed.get(0) ? 1 : -1));
		double newZ = z + rng.nextDouble() * (maxTeleportDistance * (seed.get(1) ? 1 : -1));
		target.attemptTeleport(newX, y, newZ);
		if(!target.getEntityWorld().isRemote) {
			target.getEntityWorld().playSound(null, target.prevPosX, target.prevPosY, target.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, target.getSoundCategory(), 1.0F, 1.0F);
		}
	}
}
