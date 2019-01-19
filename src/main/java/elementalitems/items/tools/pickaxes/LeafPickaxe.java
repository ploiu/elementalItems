package elementalitems.items.tools.pickaxes;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.items.ISharedLeafEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Leaf pickaxe.
 */
public class LeafPickaxe extends BasePickaxe implements ISharedLeafEffect {
	/**
	 * Instantiates a new Leaf pickaxe.
	 */
	public LeafPickaxe() {
		super(ElementalTypes.LEAF);
	}

	@Override
	public void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		this.applyLeafEffect(world, position, 10);
	}
}
