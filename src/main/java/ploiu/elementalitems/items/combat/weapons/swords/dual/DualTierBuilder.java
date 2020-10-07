package ploiu.elementalitems.items.combat.weapons.swords.dual;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.util.ElementalUtils;

import static java.lang.Math.max;

public class DualTierBuilder {

	public static IItemTier buildTier(ElementalTypes first, ElementalTypes second) throws IllegalArgumentException {
		final IItemTier tier;
		if(first == second) {
			throw new IllegalArgumentException("Both types cannot be the same!");
		} else {
			final IItemTier firstTier = ElementalUtils.getToolMaterialFromElementalType(first);
			final IItemTier secondTier = ElementalUtils.getToolMaterialFromElementalType(second);
			// create a tier with all the best stats from each tier
			tier = new IItemTier() {
				@Override
				public int getMaxUses() {
					return max(firstTier.getMaxUses(), secondTier.getMaxUses());
				}

				@Override
				public float getEfficiency() {
					return max(firstTier.getMaxUses(), secondTier.getMaxUses());
				}

				@Override
				public float getAttackDamage() {
					return max(firstTier.getAttackDamage(), secondTier.getAttackDamage());
				}

				@Override
				public int getHarvestLevel() {
					return max(firstTier.getHarvestLevel(), secondTier.getHarvestLevel());
				}

				@Override
				public int getEnchantability() {
					return max(firstTier.getEnchantability(), secondTier.getEnchantability());
				}

				@Override
				public Ingredient getRepairMaterial() {
					return Ingredient.fromItems(ElementalUtils.getCrystalForElementalType(first), ElementalUtils.getCrystalForElementalType(second));
				}
			};
		}
		return tier;
	}
}
