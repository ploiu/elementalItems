package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ItemTier.DIAMOND;
import static net.minecraft.item.ItemTier.GOLD;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.enderCrystal;

public class EnderItemTier implements IItemTier {
	@Override
	public int getUses() {
		return 2000;
	}

	@Override
	public float getSpeed() {
		return DIAMOND.getSpeed();
	}

	@Override
	public float getAttackDamageBonus() {
		return 10f;
	}

	@Override
	public int getLevel() {
		return DIAMOND.getLevel() + 1;
	}

	@Override
	public int getEnchantmentValue() {
		return GOLD.getEnchantmentValue();
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(enderCrystal);
	}
}
