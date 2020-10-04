package ploiu.elementalitems.items.combat.weapons;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ploiu.elementalitems.items.ElementalItem;

public interface BaseWeapon extends ElementalItem {
	boolean applyEffect(ItemStack stack, LivingEntity target, LivingEntity user);
}
