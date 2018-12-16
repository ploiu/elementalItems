package elementalitems.util;

import elementalitems.ElementalItems;
import elementalitems.ElementalType;
import elementalitems.entities.arrows.*;
import elementalitems.items.ElementalItem;
import elementalitems.items.ItemHandler;
import elementalitems.items.combat.armor.BaseArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class EntityUtils {
	private EntityUtils() {
	}

	public static EntityUtils getInstance() {
		return EntityUtils.SingletonHelper.instance;
	}

	public boolean isValidEntityLivingBase(Entity toCheck) {
		return toCheck instanceof EntityLivingBase && toCheck.isEntityAlive();
	}

	public boolean isMobFromBiome(final EntityLivingBase toCheck, final Biome biome) {
		if(this.isValidEntityLivingBase(toCheck)) {
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

	public void spawnXpOrb(World world, BlockPos positionToSpawn, int xpAmount) {
		if(!world.isRemote) {
			world.spawnEntity(new EntityXPOrb(world, positionToSpawn.getX(), positionToSpawn.getY(), positionToSpawn.getZ(), xpAmount));
		}
	}

	public int getNumberOfFreeSlotsInPlayerInventory(@Nonnull EntityPlayer player) {
		int freeSlots = 0;
		for(ItemStack currentStack : player.inventory.mainInventory) {
			if(currentStack.isEmpty()) {
				freeSlots++;
			}
		}
		return freeSlots;
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
		boolean ableToAdd;
		for(ItemStack stack : itemsToAdd) {
			// attempt to add the current item to the player's inventory
			ableToAdd = player.addItemStackToInventory(stack);
			// add the current stack to leftOverItems if we couldn't add it to the player's inventory
			if(!ableToAdd) {
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

	public boolean doesEntityHaveFullElementalSetOfType(Entity toCheck, ElementalType type) {
		if(this.isValidEntityLivingBase(toCheck)) {
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

	public int getPLayerLevel(Entity player) {
		return this.isValidEntityLivingBase(player) && player instanceof EntityPlayer ? ((EntityPlayer) player).experienceLevel : 0;
	}

	public ElementalItem getArrowItemFromElementalType(ElementalType type) {
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

	public BaseEntityArrow createArrow(@Nonnull ElementalType type, World world, double x, double y, double z) {
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
				ElementalItems.logger.log(Level.FATAL, "Error when creating arrow!\n" + "From type: " + type.getTypeName());
				// we don't want to crash their game, so just print something instead of returning null
				return new EntityPlainArrow(world, x, y, z);
		}
	}

	private static class SingletonHelper {
		private static final EntityUtils instance = new EntityUtils();
	}
}
