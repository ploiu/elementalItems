package elementalitems.sharedeffects.combat;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public interface ISharedEnderCombatEffect {
	default void teleportEntity(EntityLivingBase target) {
		Random rng = target.getRNG();
		Integer maxTeleportDistance = 10;
		double newX = target.posX + (rng.nextDouble() - 0.5D) * 16.0D;
		// don't try and bury the target
		double newY = Math.max(target.posY, MathHelper.clamp(target.posY + (double) (rng.nextInt(maxTeleportDistance) - maxTeleportDistance / 2), 0.0D, (double) (target.getEntityWorld().getActualHeight() - 1)));
		double newZ = target.posZ + (rng.nextDouble() - 0.5D) * 16.0D;
		if(!target.getEntityWorld().isRemote) {
			target.setPositionAndUpdate(newX, newY, newZ);
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
