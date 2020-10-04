package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ItemTier.DIAMOND;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.waterCrystal;

public class WaterItemTier implements IItemTier {
	@Override
	public int getMaxUses() {
		return 2000;
	}

	@Override
	public float getEfficiency() {
		return 50f;
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
	public int getEnchantability() {
		return DIAMOND.getEnchantability();
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(waterCrystal);
	}
}
