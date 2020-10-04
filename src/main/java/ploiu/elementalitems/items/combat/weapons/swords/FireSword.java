package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import ploiu.elementalitems.ElementalTypes;

public class FireSword extends BaseSword {

	public FireSword() {
		super(ElementalTypes.FIRE);
	}

	@Override
	public boolean applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
		return false;
	}
}
