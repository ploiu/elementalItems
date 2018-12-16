package elementalitems.items.tools.pickaxes;

import elementalitems.ElementalType;
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
		super(ElementalType.WATER);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// no op
	}

}
