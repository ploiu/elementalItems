package elementalitems.util;

import elementalitems.ElementalType;
import elementalitems.items.ElementalItem;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.ILockableContainer;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Utils {
	private static Map<Block, ItemStack> blockToSmeltingResultMap = new LinkedHashMap<>();

	private Utils() {

	}

	public static Utils getInstance() {
		return Utils.SingletonHelper.instance;
	}

	public ItemStack getSmeltedResultWithCorrectMeta(IBlockState state) {
		Block block = state.getBlock();
		return FurnaceRecipes.instance().getSmeltingResult(new ItemStack(Item.getItemFromBlock(block), 1, block.damageDropped(state)));
	}

	public void createBlockSmeltMap() {
		ForgeRegistries.BLOCKS.forEach(block -> blockToSmeltingResultMap.put(block, FurnaceRecipes.instance().getSmeltingResult(new ItemStack(block))));
	}

	public Map<Block, ItemStack> getBlockToSmeltingResultMap() {
		return blockToSmeltingResultMap;
	}

	public void setBlockToSmeltingResultMap(Map<Block, ItemStack> blockToSmeltingResultMap) {
		Utils.blockToSmeltingResultMap = blockToSmeltingResultMap;
	}

	public List<ItemStack> getContainerContents(@Nonnull ILockableContainer container, boolean clearSlots) {
		ArrayList<ItemStack> containerItemStacks = new ArrayList<>();
		// get the size of the inventory
		int containerInventorySize = container.getSizeInventory();
		for(int i = 0; i < containerInventorySize; i++) {
			// the item stack at slot i
			ItemStack currentStack = container.getStackInSlot(i);
			// don't add if the stack is empty
			if(!currentStack.isEmpty()) {
				containerItemStacks.add(currentStack);
				// clear the slot if clearSlots says to
				if(clearSlots) {
					container.removeStackFromSlot(i);
				}
			}
		}
		return containerItemStacks;
	}

	public Item.ToolMaterial getToolMaterialFromElementalType(ElementalType type) {
		switch(type) {
			case FIRE:
				return ElementalMaterials.getInstance().TOOL_FIRE;
			case WATER:
				return ElementalMaterials.getInstance().TOOL_WATER;
			case EARTH:
				return ElementalMaterials.getInstance().TOOL_EARTH;
			case AIR:
				return ElementalMaterials.getInstance().TOOL_AIR;
			case ICE:
				return ElementalMaterials.getInstance().TOOL_ICE;
			case ENDER:
				return ElementalMaterials.getInstance().TOOL_ENDER;
			case LEAF:
				return ElementalMaterials.getInstance().TOOL_LEAF;
			case PLAIN:
			default:
				return ElementalMaterials.getInstance().TOOL_PLAIN;
		}
	}

	public ItemArmor.ArmorMaterial getArmorMaterialFromElementalType(ElementalType type) {
		switch(type) {
			case FIRE:
				return ElementalMaterials.getInstance().ARMOR_FIRE;
			case WATER:
				return ElementalMaterials.getInstance().ARMOR_WATER;
			case EARTH:
				return ElementalMaterials.getInstance().ARMOR_EARTH;
			case AIR:
				return ElementalMaterials.getInstance().ARMOR_AIR;
			case ICE:
				return ElementalMaterials.getInstance().ARMOR_ICE;
			case ENDER:
				return ElementalMaterials.getInstance().ARMOR_ENDER;
			case LEAF:
				return ElementalMaterials.getInstance().ARMOR_LEAF;
			case PLAIN:
			default:
				return ElementalMaterials.getInstance().ARMOR_PLAIN;
		}
	}

	public ElementalItem getCrystalFromElementalType(ElementalType type) {
		switch(type) {
			case FIRE:
				return ItemHandler.fireCrystal;
			case WATER:
				return ItemHandler.waterCrystal;
			case EARTH:
				return ItemHandler.earthCrystal;
			case AIR:
				return ItemHandler.airCrystal;
			case ICE:
				return ItemHandler.iceCrystal;
			case ENDER:
				return ItemHandler.enderCrystal;
			case LEAF:
				return ItemHandler.leafCrystal;
			case PLAIN:
			default:
				return ItemHandler.plainCrystal;
		}
	}

	private static class SingletonHelper {
		private static final Utils instance = new Utils();
	}

}
