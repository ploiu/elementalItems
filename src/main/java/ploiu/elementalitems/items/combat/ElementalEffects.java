package ploiu.elementalitems.items.combat;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3i;
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
			target.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 200, 1));
			target.addEffect(new EffectInstance(Effects.WEAKNESS, 200, 1));
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
			target.setSecondsOnFire(200);
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
			final World world = target.level;
			if(!world.isClientSide()) {
				world.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.BAT_TAKEOFF, SoundCategory.NEUTRAL, 1f, 1f);
			}
			double knockbackResistance = target.getAttribute(Attributes.KNOCKBACK_RESISTANCE) != null ? target.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue() + 1 : 1;
			// knockback code adapted from MobEntity#doHurtTarget since I'm terrible at trigonometry
			float degree = (float) Math.PI / 180;
			target.knockback((float) (knockbackResistance + 1), MathHelper.sin(user.yRot * degree), -MathHelper.cos(user.yRot * degree));
			// launch it into the air, based on the target's knockBack resistance
			target.setDeltaMovement(target.getDeltaMovement().x(), knockbackResistance, target.getDeltaMovement().z());
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
			World world = target.level;
			// there doesn't need to be a body for this
			BlockPos pos = new BlockPos(target.getX(), target.getY(), target.getZ());
			int distance;
			for(int i = 0; ; i++) {
				// get the current block
				if(world.getBlockState(new BlockPos(pos.subtract(new Vector3i(0, i + 1, 0)))).getMaterial().blocksMotion()) {
					// set our distance and break
					distance = i;
					target.level.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.GENERIC_BIG_FALL, SoundCategory.NEUTRAL, 1.5f, .5f);
					break;
				}
			}
			target.setBoundingBox(target.getBoundingBox().move(0, -distance, 0));
			// TODO? target.resetPositionToBB();
			target.causeFallDamage(distance, 2);
		}
	}

	/**
	 * buries the target underground relative to the target's height
	 *
	 * @param target the entity to bury underground
	 */
	public static void bury(LivingEntity target) {
		if(isValidLivingEntity(target)) {
			float height = target.getBbHeight() + 1;
			target.setBoundingBox(target.getBoundingBox().move(0, -height, 0));
			// TODO? target.resetPositionToBB();
			final World world = target.level;
			world.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.HOSTILE_BIG_FALL, SoundCategory.NEUTRAL, 1.5f, .5f);
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
			world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (new Random().nextFloat() * 0.4F + 0.8F));
			if(!world.isClientSide()) {
				SnowballEntity snowballentity = new SnowballEntity(world, player);
				snowballentity.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
				world.addFreshEntity(snowballentity);
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
			int newX = target.getRandom().nextInt(max - min) + min + (int) target.getX();
			int newZ = target.getRandom().nextInt(max - min) + min + (int) target.getZ();
			target.teleportToWithTicket(newX, target.getY(), newZ);
			target.level.playSound(null, target.xOld, target.yOld, target.zOld, SoundEvents.ENDERMAN_TELEPORT, SoundCategory.MASTER, 1.0F, 1.0F);
			target.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
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
			if(!world.isClientSide()) {
				EnderPearlEntity enderpearl = new EnderPearlEntity(world, player);
				enderpearl.shootFromRotation(player, player.xRot, player.yRot, 0f, 1.5f, 1.0f);
				world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (new Random().nextFloat() * 0.4F + 0.8F));
				world.addFreshEntity(enderpearl);
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
			int smiteLevel = Math.min((int) Math.floor(EntityUtils.getPlayerLevel(entity) / 6f), 4) + 1;
			// remove the smite enchantment from the stack to prevent duplicate enchantments
			ItemUtils.removeEnchantmentFromItem(stack, Enchantments.SMITE);
			stack.enchant(Enchantments.SMITE, smiteLevel);
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
		stack.enchant(Enchantments.KNOCKBACK, 5);
	}
}
