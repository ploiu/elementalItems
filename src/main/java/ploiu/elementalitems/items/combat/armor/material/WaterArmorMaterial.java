package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.item.crafting.Ingredient;

import static ploiu.elementalitems.items.ElementalItemsItemRegistry.waterCrystal;

public class WaterArmorMaterial extends BaseArmorMaterial {
	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(waterCrystal);
	}

	@Override
	public String getName() {
		return "elementalitems:water";
	}
}
