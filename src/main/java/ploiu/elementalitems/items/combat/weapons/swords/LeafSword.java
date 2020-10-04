package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import ploiu.elementalitems.ElementalTypes;

public class LeafSword extends BaseSword {

	public LeafSword() {
		super(ElementalTypes.LEAF);
	}

	@Override
	public boolean applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
		return false;
	}
}
