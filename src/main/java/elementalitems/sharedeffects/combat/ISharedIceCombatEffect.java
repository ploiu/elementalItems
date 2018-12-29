package elementalitems.sharedeffects.combat;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.Random;

public interface ISharedIceCombatEffect {
	default void slowAndWeakenTarget(EntityLivingBase user, EntityLivingBase target) {
		// afflict the target with weakness and slowness for 5 seconds
		target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 2, false, true));
		target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 2, false, true));
	}

	default void throwSnowball(World world, EntityPlayer player) {
		if(!world.isRemote) {
			Random itemRand = new Random(System.currentTimeMillis());
			world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			EntitySnowball entitysnowball = new EntitySnowball(world, player);
			entitysnowball.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(entitysnowball);
		}
	}
}
