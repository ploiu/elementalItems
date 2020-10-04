package ploiu.elementalitems.util;

import net.minecraft.item.ItemStack;

public class ItemUtils {

	/**
	 * removes all enchantments with the passed name from the passed item stack
	 * @param stack
	 * @param enchantmentName
	 */
	public static void removeEnchantmentFromItem(ItemStack stack, String enchantmentName) {
		stack.getEnchantmentTagList().removeIf(tag -> tag.getString().toLowerCase().contains(enchantmentName.toLowerCase()));
	}
}
