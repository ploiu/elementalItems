package elementalitems.items.tools.shovels;

import elementalitems.ElementalTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Ice shovel.
 */
public class IceShovel extends BaseShovel {

	/**
	 * Instantiates a new Ice shovel.
	 */
	public IceShovel() {
		super(ElementalTypes.ICE);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {

	}
}
