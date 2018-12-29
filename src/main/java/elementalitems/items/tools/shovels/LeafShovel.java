package elementalitems.items.tools.shovels;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.items.ISharedLeafEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Leaf shovel.
 */
public class LeafShovel extends BaseShovel implements ISharedLeafEffect {

	/**
	 * Instantiates a new Leaf shovel.
	 */
	public LeafShovel() {
		super(ElementalTypes.LEAF);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		this.applyLeafEffect(world, position, 2);
	}
}
