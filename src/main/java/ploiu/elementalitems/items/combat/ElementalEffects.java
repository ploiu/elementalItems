package ploiu.elementalitems.items.combat;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ploiu.elementalitems.util.EntityUtils;
import ploiu.elementalitems.util.ItemUtils;

import java.util.Random;

import static ploiu.elementalitems.util.EntityUtils.isValidLivingEntity;

/**
 * a class with methods meant to be shared across multiple classes for different elemental effects
 */
public class ElementalEffects {
	/**
	 * Slows the target and weakens it through potion effects
	 *
	 * @param target the entity to affect with the potion effects
	 */
	public static void slowAndWeakenTarget(LivingEntity target) {
		if(isValidLivingEntity(target)) {
			// apply slow and weakness to the target
			target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1));
			target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200, 1));
		}
	}

	/**
	 * Sets the target on fire
	 *
	 * @param target the target to set fire on
	 */
	public static void ignite(LivingEntity target) {
		if(isValidLivingEntity(target)) {
			// 200 ticks = 10 seconds
			target.setFire(200);
		}
	}

	/**
	 * launches the passed target into the air based off of their knockback resistance
	 *
	 * @param target the entity being launched
	 * @param user   the user of this weapon
	 */
	public static void launchTarget(LivingEntity user, LivingEntity target) {
		if(isValidLivingEntity(target) && isValidLivingEntity(user)) {
			// spawn a bunch of particles around the target and play a sound
			final World world = target.getEntityWorld();
			if(!world.isRemote()) {
				world.playSound(null, target.getPosition(), SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.NEUTRAL, 1f, 1f);
			}
			target.knockBack(target, 2F, user.posX - target.posX, user.posZ - target.posZ);
			// launch it into the air, based on the target's knockBack resistance
			target.addVelocity(0, target.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getValue() + 1, 0);
		}
	}

	/**
	 * Sends the target out of the sky until they hit the ground, where they take double fall damage
	 *
	 * @param target the entity to strike out of the sky
	 */
	public static void strikeDown(LivingEntity target) {
		if(isValidLivingEntity(target)) {
			// get the world the entity is in
			World world = target.getEntityWorld();
			// there doesn't need to be a body for this
			BlockPos pos = target.getPosition();
			int distance;
			for(int i = 0; ; i++) {
				// get the current block
				if(world.getBlockState(pos.down(i + 1)).isSolid()) {
					// set our distance and break
					distance = i;
					break;
				}
			}
			target.setBoundingBox(target.getBoundingBox().offset(0, -distance, 0));
			target.resetPositionToBB();
			target.fall(distance, 2);
		}
	}

	/**
	 * buries the target underground relative to the target's height
	 *
	 * @param target the entity to bury underground
	 */
	public static void bury(LivingEntity target) {
		if(isValidLivingEntity(target)) {
			float height = target.getHeight() + 1;
			target.setBoundingBox(target.getBoundingBox().offset(0, -height, 0));
			target.resetPositionToBB();
			final World world = target.getEntityWorld();
			if(!world.isRemote()) {
				// play a sound effect
				world.playSound(null, target.getPosition(), SoundEvents.ENTITY_GENERIC_BIG_FALL, SoundCategory.NEUTRAL, 1.5f, .5f);
			}
		}
	}

	/**
	 * Throws a snowball in the direction the player is facing
	 *
	 * @param world  the world the player exists in
	 * @param player the player to throw the snowball from
	 */
	public static void throwSnowball(World world, PlayerEntity player) {
		if(isValidLivingEntity(player)) {
			world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (new Random().nextFloat() * 0.4F + 0.8F));
			if(!world.isRemote) {
				SnowballEntity snowballentity = new SnowballEntity(world, player);
				snowballentity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3F, 1.0F);
				world.addEntity(snowballentity);
			}
		}
	}

	/**
	 * Teleports the target to a random location within ±10 blocks
	 *
	 * @param target the target to teleport
	 */
	public static void teleportTarget(LivingEntity target) {
		if(isValidLivingEntity(target)) {
			// teleport the target to a random location
			int min = -10;
			int max = 10;
			int newX = target.getRNG().nextInt(max - min) + min + target.getPosition().getX();
			int newZ = target.getRNG().nextInt(max - min) + min + target.getPosition().getZ();
			target.attemptTeleport(newX, target.posY, newZ, true);
			target.getEntityWorld().playSound(null, target.prevPosX, target.prevPosY, target.prevPosZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, target.getSoundCategory(), 1.0F, 1.0F);
			target.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
		}
	}

	/**
	 * Throws an enderpearl for the passed player
	 *
	 * @param world  the world the player exists in
	 * @param player the player to throw an enderpearl for
	 */
	public static void throwEnderpearl(World world, PlayerEntity player) {
		if(isValidLivingEntity(player)) {
			if(!world.isRemote) {
				EnderPearlEntity enderpearl = new EnderPearlEntity(world, player);
				enderpearl.shoot(player, player.rotationPitch, player.rotationYaw, 0f, 1.5f, 1.0f);
				world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (new Random().nextFloat() * 0.4F + 0.8F));
				world.addEntity(enderpearl);
			}
		}
	}

	/**
	 * Enchants the passed item stack with smite. The level of smite depends on the entity's XP level
	 *
	 * @param stack  the item stack to enchant
	 * @param entity the entity that has the item stack in its inventory
	 */
	public static void enchantWithSmite(ItemStack stack, Entity entity) {
		if(isValidLivingEntity(entity)) {
			// update the smite enchantment on the stack based on the player level
			int smiteLevel = Math.min((int) Math.floor(EntityUtils.getPlayerLevel(entity) / 6f), 5);
			// remove the smite enchantment from the stack to prevent duplicate enchantments
			ItemUtils.removeEnchantmentFromItem(stack, Enchantments.SMITE);
			stack.addEnchantment(Enchantments.SMITE, smiteLevel);
		}
	}

	/**
	 * Enchants the passed item stack with knockback 5
	 *
	 * @param stack the item stack to enchant
	 */
	public static void enchantWithKnockback(ItemStack stack) {
		// remove the knockback enchantment from the stack to prevent duplicate enchantments
		ItemUtils.removeEnchantmentFromItem(stack, Enchantments.KNOCKBACK);
		stack.addEnchantment(Enchantments.KNOCKBACK, 5);
	}
}
