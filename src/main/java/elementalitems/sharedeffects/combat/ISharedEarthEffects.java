package elementalitems.sharedeffects.combat;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public interface ISharedEarthEffects {
	default void strikeDownEntity(@Nonnull EntityLivingBase target) {
		// get the world the entity is in
		World world = target.getEntityWorld();
		// there doesn't need to be a body for this
		BlockPos pos = target.getPosition();
		int distance;
		for(int i = 0; ; i++) {
			// get the current block
			if(!world.getBlockState(pos.down(i + 1)).getBlock().isPassable(world, pos)) {
				// set our distance and break
				distance = i;
				break;
			}
		}
		target.setEntityBoundingBox(target.getEntityBoundingBox().offset(0, -distance, 0));
		target.resetPositionToBB();
		target.fall(distance, 2);
	}
}
