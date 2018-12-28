package elementalitems.items.tools.pickaxes;

import elementalitems.ElementalTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Plain pickaxe.
 */
public class PlainPickaxe extends BasePickaxe {
	/**
	 * Instantiates a new Plain pickaxe.
	 */
	public PlainPickaxe() {
		super(ElementalTypes.PLAIN);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {

	}
}
