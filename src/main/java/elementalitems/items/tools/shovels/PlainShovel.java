package elementalitems.items.tools.shovels;

import elementalitems.ElementalTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Plain shovel.
 */
public class PlainShovel extends BaseShovel {
	/**
	 * Instantiates a new Plain shovel.
	 */
	public PlainShovel() {
		super(ElementalTypes.PLAIN);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {

	}
}
