package elementalitems.items.tools.pickaxes;

import elementalitems.ElementalType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Air pickaxe.
 */
public class AirPickaxe extends BasePickaxe {
	/**
	 * Instantiates a new Air pickaxe.
	 */
	public AirPickaxe() {
		super(ElementalType.AIR);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// no op
	}

}
