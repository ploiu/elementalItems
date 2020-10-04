package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.crafting.Ingredient;

import static ploiu.elementalitems.items.ElementalItemsItemRegistry.earthCrystal;

public class EarthArmorMaterial extends BaseArmorMaterial {
	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(earthCrystal);
	}

	@Override
	public String getName() {
		return "elementalitems:earth";
	}

	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		return 100;
	}

	@Override
	public float getToughness() {
		return super.getToughness() * 4;
	}
}
