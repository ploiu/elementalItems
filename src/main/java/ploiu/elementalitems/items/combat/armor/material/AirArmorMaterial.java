package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.item.crafting.Ingredient;

import static ploiu.elementalitems.items.ElementalItemsItemRegistry.airCrystal;

public class AirArmorMaterial extends BaseArmorMaterial {
	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(airCrystal);
	}

	@Override
	public String getName() {
		return "material_armor_air";
	}
}
