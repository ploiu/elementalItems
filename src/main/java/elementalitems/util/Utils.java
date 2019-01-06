package elementalitems.util;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.ILockableContainer;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	private Utils() {

	}

	public static Utils getInstance() {
		return Utils.SingletonHelper.instance;
	}

	public ItemStack getSmeltedResultWithCorrectMeta(IBlockState state) {
		Block block = state.getBlock();
		// instead of directly returning the result stack, we are going to create a new item stack to avoid modifying smelting recipes
		ItemStack resultStack = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(Item.getItemFromBlock(block), 1, block.damageDropped(state)));
		return new ItemStack(resultStack.getItem(), resultStack.getCount(), resultStack.getMetadata());
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

	private static class SingletonHelper {
		private static final Utils instance = new Utils();
	}

}
