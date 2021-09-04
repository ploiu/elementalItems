package ploiu.elementalitems.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {
	public static Map<ItemStack, ItemStack> smeltingRecipes = new HashMap<>();

	/**
	 * Gets the result of the smelting recipe for the passed input item
	 *
	 * @param input the item to get the smelting to get the smelting result of
	 * @return the resulting item stack, or {@code null} if there is no result
	 */
	@Nullable
	public static ItemStack getSmeltingResult(ItemStack input) {
		final ItemStack resultStack;
		// we can't just use the input as a key as the object won't be equal. We have to use the ItemStack's special equals method
		Optional<ItemStack> schrodingerStack = smeltingRecipes.entrySet().parallelStream()
				                                       .filter(entry -> entry.getKey().sameItem(input))
				                                       .map(Map.Entry::getValue)
				                                       .findFirst();
		resultStack = schrodingerStack.orElse(null);
		return resultStack;
	}

	public static List<ItemStack> getContainerItems(Container container) {
		final List<ItemStack> stacks = new ArrayList<>();
		if(container != null) {
			// get all the slots that have non-empty item stacks
			List<ItemStack> inventoryItems = container.getItems().stream().filter(Objects::nonNull).filter(stack -> !stack.isEmpty()).collect(Collectors.toList());
			stacks.addAll(inventoryItems);
		}
		return stacks;
	}

	public static List<ItemStack> getContainerItems(IInventory container) {
		List<ItemStack> items = new ArrayList<>();
		int inventorySize = container.getContainerSize();
		// for each slot in the container, if it's not empty or null, add it to items
		for(int i = 0; i < inventorySize; i++) {
			ItemStack currentStack = container.getItem(i);
			if(!currentStack.isEmpty()) {
				items.add(currentStack);
			}
		}
		return items;
	}
}
