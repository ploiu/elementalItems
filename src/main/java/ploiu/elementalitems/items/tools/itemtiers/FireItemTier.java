package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ItemTier.DIAMOND;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.fireCrystal;

public class FireItemTier implements IItemTier {
	@Override
	public int getMaxUses() {
		return 2000;
	}

	@Override
	public float getEfficiency() {
		return DIAMOND.getEfficiency();
	}

	@Override
	public float getAttackDamage() {
		return 5f;
	}

	@Override
	public int getHarvestLevel() {
		return DIAMOND.getHarvestLevel() + 1;
	}

	@Override
	public int getEnchantmentValue() {
		return DIAMOND.getEnchantmentValue() / 2;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(fireCrystal);
	}
}
