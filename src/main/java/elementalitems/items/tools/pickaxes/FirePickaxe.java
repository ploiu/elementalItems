package elementalitems.items.tools.pickaxes;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.items.ISharedFireEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Fire pickaxe.
 */
public class FirePickaxe extends BasePickaxe implements ISharedFireEffect {
	/**
	 * Instantiates a new Fire pickaxe.
	 */
	public FirePickaxe() {
		super(ElementalTypes.FIRE);
	}

	@Override
	public void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		this.applyFireEffect(world, state, position);
	}

}
