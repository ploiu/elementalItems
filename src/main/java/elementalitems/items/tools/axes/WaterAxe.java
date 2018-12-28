package elementalitems.items.tools.axes;

import elementalitems.ElementalTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Water axe.
 */
public class WaterAxe extends BaseAxe {

	/**
	 * Instantiates a new Water axe.
	 */
	public WaterAxe() {
		super(ElementalTypes.WATER);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// no op
	}
}
