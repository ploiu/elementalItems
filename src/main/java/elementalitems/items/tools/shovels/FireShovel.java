package elementalitems.items.tools.shovels;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.items.ISharedFireEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Fire shovel.
 */
public class FireShovel extends BaseShovel implements ISharedFireEffect {

	/**
	 * Instantiates a new Fire shovel.
	 */
	public FireShovel() {
		super(ElementalTypes.FIRE);
	}

	@Override
	public void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		this.applyFireEffect(world, state, position);
		user.getHeldItemMainhand().damageItem(1, user);
	}
}
