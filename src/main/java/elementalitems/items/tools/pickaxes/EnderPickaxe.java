package elementalitems.items.tools.pickaxes;

import elementalitems.ElementalType;
import elementalitems.sharedeffects.items.ISharedEnderEffect;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Ender pickaxe.
 */
public class EnderPickaxe extends BasePickaxe implements ISharedEnderEffect {
	/**
	 * Instantiates a new Ender pickaxe.
	 */
	public EnderPickaxe() {
		super(ElementalType.ENDER);
	}

	@Override
	protected void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		this.applyEnderEffect(world, state, position, user);

	}

}
