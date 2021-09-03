package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.item.crafting.Ingredient;

import static ploiu.elementalitems.items.ElementalItemsItemRegistry.plainCrystal;

public class PlainArmorMaterial extends BaseArmorMaterial {

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(plainCrystal);
	}

	@Override
	public String getName() {
		return "elementalitems:plain";
	}
}
