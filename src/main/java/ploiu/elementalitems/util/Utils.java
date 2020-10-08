package ploiu.elementalitems.util;

import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
				                                       .filter(entry -> entry.getKey().isItemEqual(input))
				                                       .map(Map.Entry::getValue)
				                                       .findFirst();
		resultStack = schrodingerStack.orElse(null);
		return resultStack;
	}
}
