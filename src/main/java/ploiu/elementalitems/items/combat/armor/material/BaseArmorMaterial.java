package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import static net.minecraft.item.ArmorMaterial.DIAMOND;

public abstract class BaseArmorMaterial implements IArmorMaterial {

	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		return 33;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
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
	public int getEnchantability() {
		return DIAMOND.getEnchantability();
	}

	@Override
	public SoundEvent getSoundEvent() {
		return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	}

	@Override
	public float getToughness() {
		return 2.0f;
	}
}
