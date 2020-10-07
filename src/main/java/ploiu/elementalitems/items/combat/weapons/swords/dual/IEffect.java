package ploiu.elementalitems.items.combat.weapons.swords.dual;

import net.minecraft.entity.LivingEntity;

@FunctionalInterface
public interface IEffect {
	void apply(LivingEntity user, LivingEntity target);
}