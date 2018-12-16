package elementalitems.items.tools.shovels;

import elementalitems.ElementalType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Earth shovel.
 */
public class EarthShovel extends BaseShovel {

	/**
	 * Instantiates a new Earth shovel.
	 */
	public EarthShovel() {
		super(ElementalType.EARTH);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// no op
	}
}