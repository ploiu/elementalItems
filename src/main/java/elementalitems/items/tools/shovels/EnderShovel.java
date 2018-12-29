package elementalitems.items.tools.shovels;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.items.ISharedEnderEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Ender shovel.
 */
public class EnderShovel extends BaseShovel implements ISharedEnderEffect {

	/**
	 * Instantiates a new Ender shovel.
	 */
	public EnderShovel() {
		super(ElementalTypes.ENDER);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		this.applyEnderEffect(world, state, position, user);
	}
}
