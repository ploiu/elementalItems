package ploiu.elementalitems.items.combat.weapons;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import ploiu.elementalitems.items.ElementalItem;

public interface BaseWeapon extends ElementalItem {
	void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user);
}
