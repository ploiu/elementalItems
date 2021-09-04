package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ItemTier.*;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.iceCrystal;

public class IceItemTier implements IItemTier {
	@Override
	public int getUses() {
		return 1000;
	}

	@Override
	public float getSpeed() {
		return DIAMOND.getSpeed();
	}

	@Override
	public float getAttackDamageBonus() {
		return 5f;
	}

	@Override
	public int getLevel() {
		return IRON.getLevel();
	}

	@Override
	public int getEnchantmentValue() {
		return GOLD.getEnchantmentValue() * 2;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(iceCrystal);
	}
}
