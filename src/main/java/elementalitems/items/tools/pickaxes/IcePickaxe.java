package elementalitems.items.tools.pickaxes;

import elementalitems.ElementalTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Ice pickaxe.
 */
public class IcePickaxe extends BasePickaxe {
	/**
	 * Instantiates a new Ice pickaxe.
	 */
	public IcePickaxe() {
		super(ElementalTypes.ICE);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// no op
	}

}
