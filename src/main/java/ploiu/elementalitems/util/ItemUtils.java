package ploiu.elementalitems.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.crystal.BaseCrystal;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.items.tools.itemtiers.TierRegistry;

import javax.annotation.Nonnull;

public class ItemUtils {

	/**
	 * removes all enchantments with the passed name from the passed item stack
	 *
	 * @param stack
	 * @param enchantment
	 */
	public static void removeEnchantmentFromItem(ItemStack stack, Enchantment enchantment) {
		final String enchantmentName = enchantment.getName().toLowerCase().split("\\.")[2];
		stack.getEnchantmentTagList().removeIf(tag -> tag.getString().toLowerCase().contains(enchantmentName));
	}

	/**
	 * Gets the IItemTier associated from the passed ElementalType.
	 *
	 * @param type the elementalType to get the associated item tier for
	 * @return the associated item tier
	 */
	public static IItemTier getItemTierFromType(@Nonnull ElementalTypes type) {
		switch(type) {
			case FIRE:
				return TierRegistry.fireTier;
			case ICE:
				return TierRegistry.iceTier;
			case WATER:
				return TierRegistry.waterTier;
			case LEAF:
				return TierRegistry.leafTier;
			case EARTH:
				return TierRegistry.earthTier;
			case AIR:
				return TierRegistry.airTier;
			case ENDER:
				return TierRegistry.enderTier;
			case PLAIN:
			default:
				return TierRegistry.plainTier;
		}
	}

	public static IArmorMaterial getArmorMaterialFromType(@Nonnull ElementalTypes type) {
		switch(type) {
			case FIRE:
				return TierRegistry.fireArmorMaterial;
			case ICE:
				return TierRegistry.iceArmorMaterial;
			case WATER:
				return TierRegistry.waterArmorMaterial;
			case LEAF:
				return TierRegistry.leafArmorMaterial;
			case EARTH:
				return TierRegistry.earthArmorMaterial;
			case AIR:
				return TierRegistry.airArmorMaterial;
			case ENDER:
				return TierRegistry.enderArmorMaterial;
			case PLAIN:
			default:
				return TierRegistry.plainArmorMaterial;
		}
	}


	public static BaseCrystal getCrystalForElementalType(ElementalTypes type) {
		switch(type) {
			case FIRE:
				return ElementalItemsItemRegistry.fireCrystal;
			case ICE:
				return ElementalItemsItemRegistry.iceCrystal;
			case WATER:
				return ElementalItemsItemRegistry.waterCrystal;
			case LEAF:
				return ElementalItemsItemRegistry.leafCrystal;
			case EARTH:
				return ElementalItemsItemRegistry.earthCrystal;
			case AIR:
				return ElementalItemsItemRegistry.airCrystal;
			case ENDER:
				return ElementalItemsItemRegistry.enderCrystal;
			default:
				return ElementalItemsItemRegistry.plainCrystal;
		}
	}
}
