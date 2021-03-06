package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.crafting.Ingredient;

import static ploiu.elementalitems.items.ElementalItemsItemRegistry.iceCrystal;

public class IceArmorMaterial extends BaseArmorMaterial {
	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(iceCrystal);
	}

	@Override
	public String getName() {
		return "elementalitems:ice";
	}

	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		return new int[]{13, 15, 16, 11}[slotIn.getIndex()] * 20;
	}

	@Override
	public int getEnchantability() {
		return 50;
	}
}
