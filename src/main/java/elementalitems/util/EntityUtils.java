package elementalitems.util;

import elementalitems.ElementalItems;
import elementalitems.ElementalItemsConfig;
import elementalitems.ElementalTypes;
import elementalitems.entities.arrows.*;
import elementalitems.items.ElementalItem;
import elementalitems.items.ItemHandler;
import elementalitems.items.combat.armor.BaseArmor;
import net.minecraft.block.Block;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EntityUtils {
	private EntityUtils() {
	}

	public static EntityUtils getInstance() {
		return EntityUtils.SingletonHelper.instance;
	}

	/**
	 * Checks if the passed {@link Entity} is a valid {@link EntityLivingBase} that can be operated on.
	 * toCheck is valid if it's not {@code null}, an instanceof {@link EntityLivingBase}, and is alive
	 *
	 * @param toCheck the Entity we want to check
	 * @return true if toCheck is not null and is a living instance of {@link EntityLivingBase}
	 */
	public boolean isValidEntityLivingBase(Entity toCheck) {
		boolean isValid = toCheck instanceof EntityLivingBase;
		// if we're set to check safely, ensure it's alive as well
		isValid = isValid && (ElementalItemsConfig.shouldUseLessSafeCheckToValidateEntities || toCheck.isEntityAlive());
		return isValid;
	}

	/**
	 * attempts to get the parent of the passed {@link MultiPartEntityPart} and return it
	 *
	 * @param childEntity the part of the entity we want to get
	 * @return null if we can't get the child's parent, else return the parent of the childEntity
	 */
	@Nullable
	public EntityLiving getParentEntityFromMultipart(MultiPartEntityPart childEntity) {
		if(childEntity != null && childEntity.parent != null) {
			// get if the parent is an EntityLiving as well
			if(childEntity.parent instanceof EntityLiving) {
				return (EntityLiving) childEntity.parent;
			}
		}
		// we can't really do anything...
		return null;
	}

	public boolean isMobFromBiome(final EntityLivingBase toCheck, final Biome biome) {
		if(toCheck != null && biome != null) {
			boolean isFromBiome = false;
			EnumCreatureType[] values = EnumCreatureType.values();
			for(EnumCreatureType creatureType : values) {
				List<Biome.SpawnListEntry> spawnListEntries = biome.getSpawnableList(creatureType);
				for(Biome.SpawnListEntry entry : spawnListEntries) {
					// this way it'll stay true if it's met the criteria already
					isFromBiome |= entry.entityClass.equals(toCheck.getClass());
				}
			}
			return isFromBiome;
		} else {
			return false;
		}
	}

	public void spawnXpOrb(@Nonnull World world, BlockPos positionToSpawn, int xpAmount) {
		if(!world.isRemote && positionToSpawn != null && xpAmount > 0) {
			world.spawnEntity(new EntityXPOrb(world, positionToSpawn.getX(), positionToSpawn.getY(), positionToSpawn.getZ(), xpAmount));
		}
	}

	/**
	 * attempts to add the passed itemsToAdd to the player's inventory, and returns any remaining {@link ItemStack}s to the calling method
	 *
	 * @param player     the player we want to add the items to
	 * @param itemsToAdd the list of items we are going to try to add to the player's inventory
	 * @return a List of ItemStacks that hold any remaining items that couldn't be added
	 */
	public List<ItemStack> addItemsToPlayerInventory(@Nonnull EntityPlayer player, List<ItemStack> itemsToAdd) {
		List<ItemStack> leftOverItems = new ArrayList<>();
		// try to add all items from itemToAdd to the player's inventory
		for(ItemStack stack : itemsToAdd) {
			// attempt to add the current item to the player's inventory
			boolean wasUnableToAdd = !player.addItemStackToInventory(stack);
			// add the current stack to leftOverItems if we couldn't add it to the player's inventory
			if(wasUnableToAdd) {
				leftOverItems.add(stack);
			}
		}
		return leftOverItems;
	}

	public void dropItemsInWorld(@Nonnull World world, @Nonnull EntityLivingBase target, List<ItemStack> itemsToDrop) {
		itemsToDrop.forEach(itemStack -> {
			// create a new Item to be dropped into the world
			EntityItem itemStackEntity = new EntityItem(world, target.posX, target.posY, target.posZ, itemStack);
			itemStackEntity.setDefaultPickupDelay();
			world.spawnEntity(itemStackEntity);
		});
	}

	/**
	 * drops the passed list of {@link ItemStack ItemStacks} at the passed {@link BlockPos}
	 *
	 * @param world       the {@link World} that will spawn the items
	 * @param position    the BlockPos to drop the items at
	 * @param itemsToDrop the actual items that are being spawned into the world
	 */
	public void dropItemsInWorld(@Nonnull World world, @Nonnull BlockPos position, @Nonnull List<ItemStack> itemsToDrop) {
		for(ItemStack itemStack : itemsToDrop) {
			Block.spawnAsEntity(world, position, itemStack);
		}
	}

	/**
	 * Takes an {@link Entity} and checks its armorInventoryList for a full set of {@link BaseArmor} where each {@code currentArmor.type == type}
	 *
	 * @param toCheck the entity to check
	 * @param type    the {@link ElementalTypes} we are checking for
	 * @return true if all 4 pieces of the wearer's armor is the same type as {@code type}, false otherwise
	 */
	public boolean doesEntityHaveFullElementalSetOfType(@Nullable Entity toCheck, ElementalTypes type) {
		if(this.isValidEntityLivingBase(toCheck) && type != null) {
			// do we have a full set?
			boolean fullSet = true;
			// iterate through toCheck's armor inventory and check for BaseArmor with the same type as what's passed in
			Iterable<ItemStack> armorItems = toCheck.getArmorInventoryList();
			for(ItemStack stack : armorItems) {
				if(stack == null || !(stack.getItem() instanceof BaseArmor) || ((BaseArmor) stack.getItem()).getType() != type) {
					fullSet = false;
				}
			}
			return fullSet;
		}
		return false;
	}

	public int getPlayerLevel(Entity player) {
		return this.isValidEntityLivingBase(player) && player instanceof EntityPlayer ? ((EntityPlayer) player).experienceLevel : 0;
	}

	public ElementalItem getArrowItemFromElementalType(ElementalTypes type) {
		switch(type) {
			case FIRE:
				return ItemHandler.fireArrow;
			case WATER:
				return ItemHandler.waterArrow;
			case EARTH:
				return ItemHandler.earthArrow;
			case AIR:
				return ItemHandler.airArrow;
			case ICE:
				return ItemHandler.iceArrow;
			case ENDER:
				return ItemHandler.enderArrow;
			case LEAF:
				return ItemHandler.leafArrow;
			case PLAIN:
			default:
				return ItemHandler.plainArrow;
		}
	}

	public BaseEntityArrow createArrow(@Nonnull ElementalTypes type, World world, double x, double y, double z) {
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
				ElementalItems.logger.log(Level.ERROR, "Error when creating arrow!\n" + "From type: " + type.getTypeName());
				// we don't want to crash their game, so just print something instead of returning null
				return new EntityPlainArrow(world, x, y, z);
		}
	}

	private static class SingletonHelper {
		private static final EntityUtils instance = new EntityUtils();
	}
}
