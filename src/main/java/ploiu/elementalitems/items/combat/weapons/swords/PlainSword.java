package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import ploiu.elementalitems.ElementalTypes;

public class PlainSword extends BaseSword {

	public PlainSword() {
		super(ElementalTypes.PLAIN);
	}

	@Override
	public boolean applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
		return false;
	}
}
