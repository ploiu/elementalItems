package elementalitems.entities.arrows;

import elementalitems.items.ElementalItem;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

/**
 * The interface Elemental arrow.
 */
public interface ElementalArrow extends ElementalItem {
	/**
	 * called when this entity strikes another entity
	 *
	 * @param target the target we have collided with
	 */
	void applyEffectOnEntity(EntityLivingBase target);

	/**
	 * called when this arrow hits a {@link Block}
	 *
	 * @param positionHit the position we hit the block at
	 * @param sideHit     the {@link EnumFacing} we hit the block at
	 */
	void applyLandingEffect(BlockPos positionHit, EnumFacing sideHit);
}
