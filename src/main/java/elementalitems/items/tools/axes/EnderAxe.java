package elementalitems.items.tools.axes;

import elementalitems.ElementalType;
import elementalitems.sharedeffects.items.ISharedEnderEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Ender axe.
 */
public class EnderAxe extends BaseAxe implements ISharedEnderEffect {

	/**
	 * Instantiates a new Ender axe.
	 */
	public EnderAxe() {
		super(ElementalType.ENDER);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		this.applyEnderEffect(world, state, position, user);
	}
}