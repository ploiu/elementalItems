package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import static net.minecraft.item.ArmorMaterial.DIAMOND;

public abstract class BaseArmorMaterial implements IArmorMaterial {

	@Override
	public int getDurabilityForSlot(EquipmentSlotType slotIn) {
		return new int[]{13, 15, 16, 11}[slotIn.getIndex()] * 33;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlotType slotIn) {
		switch(slotIn) {
			case FEET:
			case HEAD:
				return 4;
			case LEGS:
				return 7;
			case CHEST:
				return 9;
			default:
				return 0;
		}
	}

	@Override
	public int getEnchantmentValue() {
		return DIAMOND.getEnchantmentValue();
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ARMOR_EQUIP_DIAMOND;
	}

	@Override
	public float getKnockbackResistance() {
		return DIAMOND.getKnockbackResistance();
	}

	@Override
	public float getToughness() {
		return 2.0f;
	}
}
