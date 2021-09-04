package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ItemTier.DIAMOND;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.airCrystal;

public class AirItemTier implements IItemTier {
	@Override
	public int getUses() {
		return 2000;
	}

	@Override
	public float getSpeed() {
		return 10f;
	}

	@Override
	public float getAttackDamageBonus() {
		return 5f;
	}

	@Override
	public int getLevel() {
		return DIAMOND.getLevel() + 1;
	}

	@Override
	public int getEnchantmentValue() {
		return DIAMOND.getEnchantmentValue();
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(airCrystal);
	}
}
