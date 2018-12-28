package elementalitems.items.combat.swords;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@FunctionalInterface
public interface IRightClickEffect {
	void apply(World world, EntityPlayer user);
}
