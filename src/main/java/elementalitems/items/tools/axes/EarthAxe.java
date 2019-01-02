package elementalitems.items.tools.axes;

import elementalitems.ElementalTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Earth axe.
 */
public class EarthAxe extends BaseAxe {

	/**
	 * Instantiates a new Earth axe.
	 */
	public EarthAxe() {
		super(ElementalTypes.EARTH);
	}

	@Override
	public void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// no op
	}
}
