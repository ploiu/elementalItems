package elementalitems.items.tools.axes;

import elementalitems.ElementalTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Ice axe.
 */
public class IceAxe extends BaseAxe {

	/**
	 * Instantiates a new Ice axe.
	 */
	public IceAxe() {
		super(ElementalTypes.ICE);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// no op
	}
}
