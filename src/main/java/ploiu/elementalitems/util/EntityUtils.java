package ploiu.elementalitems.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import ploiu.elementalitems.ElementalItems;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.arrow.*;
import ploiu.elementalitems.items.combat.armor.BaseArmor;
import ploiu.elementalitems.items.combat.armor.BaseArmorItem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EntityUtils {
	private EntityUtils() {
	}

	/**
	 * Checks if the passed {@link Entity} is a valid {@link LivingEntity} that can be operated on.
	 * toCheck is valid if it's not {@code null}, an instanceof {@link LivingEntity}, and is alive
	 *
	 * @param toCheck the Entity we want to check
	 * @return true if toCheck is not null and is a living instance of {@link LivingEntity}
	 */
	public static boolean isValidLivingEntity(Entity toCheck) {
		if(toCheck == null) {
			return false;
		}
		boolean isValid = toCheck instanceof LivingEntity;
		// if we're set to check safely, ensure it's alive as well
		isValid = isValid && toCheck.isAlive();
		return isValid;
	}

	/**
	 * Spawns an experience orb into the world
	 *
	 * @param world
	 * @param positionToSpawn
	 * @param xpAmount
	 */
	public static void spawnXpOrb(@Nonnull World world, BlockPos positionToSpawn, int xpAmount) {
		if(!world.isClientSide() && positionToSpawn != null && xpAmount > 0) {
			world.addFreshEntity(new ExperienceOrbEntity(world, positionToSpawn.getX(), positionToSpawn.getY(), positionToSpawn.getZ(), xpAmount));
		}
	}

	/**
	 * attempts to add the passed itemsToAdd to the player's inventory, and returns any remaining {@link ItemStack}s to the calling method
	 *
	 * @param player     the player we want to add the items to
	 * @param itemsToAdd the list of items we are going to try to add to the player's inventory
	 * @return a List of ItemStacks that hold any remaining items that couldn't be added
	 */
	public static List<ItemStack> addItemsToPlayerInventory(@Nonnull PlayerEntity player, List<ItemStack> itemsToAdd) {
		List<ItemStack> leftOverItems = new ArrayList<>();
		// try to add all items from itemToAdd to the player's inventory
		for(ItemStack stack : itemsToAdd) {
			// attempt to add the current item to the player's inventory
			boolean wasUnableToAdd = !player.addItem(stack);
			// add the current stack to leftOverItems if we couldn't add it to the player's inventory
			if(wasUnableToAdd) {
				leftOverItems.add(stack);
			}
		}
		return leftOverItems;
	}

	public static void dropItemsInWorld(@Nonnull World world, @Nonnull LivingEntity target, List<ItemStack> itemsToDrop) {
		itemsToDrop.forEach(itemStack -> {
			// create a new Item to be dropped into the world
			ItemEntity itemStackEntity = new ItemEntity(world, target.getX(), target.getY(), target.getZ(), itemStack);
			itemStackEntity.setDefaultPickUpDelay();
			world.addFreshEntity(itemStackEntity);
		});
	}

	/**
	 * drops the passed list of {@link ItemStack ItemStacks} at the passed {@link BlockPos}
	 *
	 * @param world       the {@link World} that will spawn the items
	 * @param position    the BlockPos to drop the items at
	 * @param itemsToDrop the actual items that are being spawned into the world
	 */
	public static void dropItemsInWorld(@Nonnull World world, @Nonnull BlockPos position, @Nonnull List<ItemStack> itemsToDrop) {
		for(ItemStack itemStack : itemsToDrop) {
			world.addFreshEntity(new ItemEntity(world, position.getX(), position.getY(), position.getZ(), itemStack));
		}
	}

	/**
	 * Takes an {@link Entity} and checks its armorInventoryList for a full set of {@link BaseArmor} where each {@code currentArmor.type == type}
	 *
	 * @param type    the {@link ElementalTypes} we are checking for
	 * @param toCheck the entity to check
	 * @return true if all 4 pieces of the wearer's armor is the same type as {@code type}, false otherwise
	 */
	public static boolean doesEntityHaveFullElementalSetOfType(ElementalTypes type, @Nullable Entity toCheck) {
		if(isValidLivingEntity(toCheck) && type != null) {
			// do we have a full set?
			boolean fullSet = true;
			// iterate through toCheck's armor inventory and check for BaseArmor with the same type as what's passed in
			Iterable<ItemStack> armorItems = toCheck.getArmorSlots();
			for(ItemStack stack : armorItems) {
				if(stack == null || !(stack.getItem() instanceof BaseArmor) || ((BaseArmor) stack.getItem()).getType() != type) {
					fullSet = false;
				}
			}
			return fullSet;
		}
		return false;
	}

	/**
	 * Gets the equipped armor for the passed entity
	 *
	 * @param target
	 * @return
	 */
	@Nonnull
	public static List<ItemStack> getEntityArmor(@Nullable Entity target) {
		List<ItemStack> armorInventory = new ArrayList<>();
		if(isValidLivingEntity(target)) {
			Iterable<ItemStack> armorIterable = target.getArmorSlots();
			armorIterable.forEach(armorInventory::add);
		}
		// now remove empty item stacks, and item stacks whose item is not an armor piece
		return armorInventory.stream()
				       .filter(Objects::nonNull)
				       .filter(itemStack -> !itemStack.isEmpty())
				       .filter(itemStack -> itemStack.getItem() instanceof ArmorItem)
				       .collect(Collectors.toList());
	}

	/**
	 * Gets the number of pieces of armor worn for the passed elemental type on the passed entity
	 *
	 * @param type   the elemental type to count the equipped armor for
	 * @param target the entity to get the equipped armor of
	 * @return the number of pieces of armor worn that match the passed elemental type
	 */
	public static int getNumberOfElementalArmorForType(ElementalTypes type, Entity target) {
		if(isValidLivingEntity(target)) {
			List<ItemStack> armorList = getEntityArmor(target);
			return (int) armorList.stream().filter(armor -> armor.getItem() instanceof BaseArmorItem && ((BaseArmorItem) armor.getItem()).getType() == type).count();
		} else {
			return 0;
		}
	}

	public static boolean doesEntityHaveElementalArmorEquipped(ElementalTypes type, Entity target) {
		return getNumberOfElementalArmorForType(type, target) > 0;
	}

	/**
	 * Gets the level of the passed entity
	 *
	 * @param player
	 * @return
	 */
	public static int getPlayerLevel(Entity player) {
		return isValidLivingEntity(player) && player instanceof PlayerEntity ? ((PlayerEntity) player).experienceLevel : 0;
	}

	public static boolean doesEntityHaveArmorPieceEquipped(Entity entity, ArmorItem armorPiece) {
		List<ItemStack> armorPieces = getEntityArmor(entity);
		armorPieces.removeIf(piece -> !armorPiece.equals(piece.getItem()));
		return armorPieces.size() > 0;
	}

	public static BaseEntityArrow createArrow(@Nonnull ElementalTypes type, World world, double x, double y, double z) {
		switch(type) {
			case FIRE:
				return new EntityFireArrow(world, x, y, z);
			case WATER:
				return new EntityWaterArrow(world, x, y, z);
			case EARTH:
				return new EntityEarthArrow(world, x, y, z);
			case AIR:
				return new EntityAirArrow(world, x, y, z);
			case ICE:
				return new EntityIceArrow(world, x, y, z);
			case ENDER:
				return new EntityEnderArrow(world, x, y, z);
			case LEAF:
				return new EntityLeafArrow(world, x, y, z);
			case PLAIN:
				return new EntityPlainArrow(world, x, y, z);
			default:
				ElementalItems.LOGGER.log(Level.ERROR, "Error when creating arrow!\n" + "From type: " + type.getTypeName());
				// we don't want to crash their game, so just print something instead of returning null
				return new EntityPlainArrow(world, x, y, z);
		}
	}
	
	public static BaseEntityArrow createArrow(@Nonnull ElementalTypes type, World world, LivingEntity shooter) {
		switch(type) {
			case FIRE:
				return new EntityFireArrow(world, shooter);
			case WATER:
				return new EntityWaterArrow(world, shooter);
			case EARTH:
				return new EntityEarthArrow(world, shooter);
			case AIR:
				return new EntityAirArrow(world, shooter);
			case ICE:
				return new EntityIceArrow(world, shooter);
			case ENDER:
				return new EntityEnderArrow(world, shooter);
			case LEAF:
				return new EntityLeafArrow(world, shooter);
			case PLAIN:
				return new EntityPlainArrow(world, shooter);
			default:
				ElementalItems.LOGGER.log(Level.ERROR, "Error when creating arrow!\n" + "From type: " + type.getTypeName());
				// we don't want to crash their game, so just print something instead of returning null
				return new EntityPlainArrow(world, shooter);
		}
	}
}