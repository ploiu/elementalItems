package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ItemTier.DIAMOND;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.leafCrystal;

public class LeafItemTier implements IItemTier {
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
		return 15f;
	}

	@Override
	public int getLevel() {
		return DIAMOND.getLevel() + 1;
	}

	@Override
	public int getEnchantmentValue() {
		return 15;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(leafCrystal);
	}
}
