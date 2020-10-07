package ploiu.elementalitems.items.combat.weapons.swords.dual;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@FunctionalInterface
public interface IRightClickEffect {
	void apply(World world, PlayerEntity user);
}
