package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;

import static net.minecraft.item.ItemTier.DIAMOND;

public class PlainItemTier implements IItemTier {
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
		return Ingredient.of(ElementalItemsItemRegistry.plainCrystal);
	}
}
