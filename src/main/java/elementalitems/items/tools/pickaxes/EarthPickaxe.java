package elementalitems.items.tools.pickaxes;

import elementalitems.ElementalTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Earth pickaxe.
 */
public class EarthPickaxe extends BasePickaxe {
	/**
	 * Instantiates a new Earth pickaxe.
	 */
	public EarthPickaxe() {
		super(ElementalTypes.EARTH);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// no op
	}

}
