package elementalitems.items.tools.axes;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.items.ISharedLeafEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Leaf axe.
 */
public class LeafAxe extends BaseAxe implements ISharedLeafEffect {

	/**
	 * Instantiates a new Leaf axe.
	 */
	public LeafAxe() {
		super(ElementalTypes.LEAF);
	}

	@Override
	public void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		this.applyLeafEffect(world, position, 10);
	}
}
