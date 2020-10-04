package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ItemTier.DIAMOND;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.earthCrystal;

public class EarthItemTier implements IItemTier {
	@Override
	public int getMaxUses() {
		return 4000;
	}

	@Override
	public float getEfficiency() {
		return DIAMOND.getEfficiency();
	}

	@Override
	public float getAttackDamage() {
		return 5;
	}

	@Override
	public int getHarvestLevel() {
		return DIAMOND.getHarvestLevel() + 1;
	}

	@Override
	public int getEnchantability() {
		return DIAMOND.getEnchantability();
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(earthCrystal);
	}
}
