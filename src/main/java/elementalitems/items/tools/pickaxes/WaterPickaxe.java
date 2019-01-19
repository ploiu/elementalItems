package elementalitems.items.tools.pickaxes;

import elementalitems.ElementalTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Water pickaxe.
 */
public class WaterPickaxe extends BasePickaxe {
	/**
	 * Instantiates a new Water pickaxe.
	 */
	public WaterPickaxe() {
		super(ElementalTypes.WATER);
	}

	@Override
	public void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// no op
	}
}
