package elementalitems.items.tools.axes;

import elementalitems.ElementalTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Plain axe.
 */
public class PlainAxe extends BaseAxe {
	/**
	 * Instantiates a new Plain axe.
	 */
	public PlainAxe() {
		super(ElementalTypes.PLAIN);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// no op
	}
}
