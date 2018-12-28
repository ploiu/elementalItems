package elementalitems.items.tools.shovels;

import elementalitems.ElementalTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Air shovel.
 */
public class AirShovel extends BaseShovel {

	/**
	 * Instantiates a new Air shovel.
	 */
	public AirShovel() {
		super(ElementalTypes.AIR);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// no op
	}
}
