package elementalitems.sharedeffects.items;

import elementalitems.util.EntityUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ISharedLeafEffect {

	default void applyLeafEffect(World world, BlockPos position, int xpAmount) {
		EntityUtils.getInstance().spawnXpOrb(world, position, xpAmount);
	}

}
