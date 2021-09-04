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
				public int getUses() {
					return max(firstTier.getUses(), secondTier.getUses());
				}

				@Override
				public float getSpeed() {
					return max(firstTier.getSpeed(), secondTier.getSpeed());
				}

				@Override
				public float getAttackDamageBonus() {
					return max(firstTier.getAttackDamageBonus(), secondTier.getAttackDamageBonus());
				}

				@Override
				public int getLevel() {
					return max(firstTier.getLevel(), secondTier.getLevel());
				}

				@Override
				public int getEnchantmentValue() {
					return max(firstTier.getEnchantmentValue(), secondTier.getEnchantmentValue());
				}

				@Override
				public Ingredient getRepairIngredient() {
					return Ingredient.of(ElementalUtils.getCrystalForElementalType(first), ElementalUtils.getCrystalForElementalType(second));
				}
			};
		}
		return tier;
	}
}
