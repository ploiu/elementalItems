package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ItemTier.STONE;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.fireCrystal;

public class FireArmorMaterial extends BaseArmorMaterial {
	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(fireCrystal);
	}

	@Override
	public String getName() {
		return "elementalitems:fire";
	}

	@Override
	public int getEnchantmentValue() {
		return STONE.getEnchantmentValue();
	}
}
