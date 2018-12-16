package elementalitems.items.tools.axes;

import elementalitems.ElementalType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Air axe.
 */
public class AirAxe extends BaseAxe {

	/**
	 * Instantiates a new Air axe.
	 */
	public AirAxe() {
		super(ElementalType.AIR);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// no op
	}
}
