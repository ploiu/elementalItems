package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ItemTier.DIAMOND;
import static net.minecraft.item.ItemTier.GOLD;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.enderCrystal;

public class EnderItemTier implements IItemTier {
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
		return 10f;
	}

	@Override
	public int getHarvestLevel() {
		return DIAMOND.getHarvestLevel() + 1;
	}

	@Override
	public int getEnchantability() {
		return GOLD.getEnchantability();
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(enderCrystal);
	}
}
