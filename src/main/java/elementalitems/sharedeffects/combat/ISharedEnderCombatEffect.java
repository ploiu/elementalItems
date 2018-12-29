package elementalitems.sharedeffects.combat;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public interface ISharedEnderCombatEffect {
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

	default void throwEnderPearl(World world, EntityPlayer player) {
		if(!world.isRemote) {
			Random itemRand = new Random(System.currentTimeMillis());
			world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			// we need to spawn an ender pearl into the world
			EntityEnderPearl enderPearl = new EntityEnderPearl(world, player);
			enderPearl.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(enderPearl);
		}
	}
}
