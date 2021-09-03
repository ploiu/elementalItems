package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.crafting.Ingredient;

import static ploiu.elementalitems.items.ElementalItemsItemRegistry.earthCrystal;

public class EarthArmorMaterial extends BaseArmorMaterial {
	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(earthCrystal);
	}

	@Override
	public String getName() {
		return "elementalitems:earth";
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlotType slotIn) {
		return new int[]{13, 15, 16, 11}[slotIn.getIndex()] * 100;
	}

	@Override
	public float getToughness() {
		return super.getToughness() * 4;
	}
}
