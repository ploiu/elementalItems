package elementalitems.items.tools.axes;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.items.ISharedFireEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Fire axe.
 */
public class FireAxe extends BaseAxe implements ISharedFireEffect {
	/**
	 * Instantiates a new Fire axe.
	 */
	public FireAxe() {
		super(ElementalTypes.FIRE);
	}

	@Override
	public void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		this.applyFireEffect(world, state, position);
		user.getHeldItemMainhand().damageItem(1, user);
	}

}
