package elementalitems.items.tools.shovels;

import elementalitems.ElementalTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Water shovel.
 */
public class WaterShovel extends BaseShovel {

	/**
	 * Instantiates a new Water shovel.
	 */
	public WaterShovel() {
		super(ElementalTypes.WATER);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {

	}
}
