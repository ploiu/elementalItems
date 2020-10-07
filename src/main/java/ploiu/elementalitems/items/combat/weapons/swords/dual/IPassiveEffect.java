package ploiu.elementalitems.items.combat.weapons.swords.dual;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

@FunctionalInterface
public interface IPassiveEffect {
	void apply(ItemStack thisItem, Entity owner);
}