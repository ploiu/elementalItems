package elementalitems.items.combat.swords.dualEffects;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@FunctionalInterface
public interface IRightClickEffect {
	void apply(World world, EntityPlayer user);
}
