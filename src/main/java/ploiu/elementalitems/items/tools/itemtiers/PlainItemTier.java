package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;

import static net.minecraft.item.ItemTier.DIAMOND;

public class PlainItemTier implements IItemTier {
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
		return DIAMOND.getEnchantmentValue();
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(ElementalItemsItemRegistry.plainCrystal);
	}
}
