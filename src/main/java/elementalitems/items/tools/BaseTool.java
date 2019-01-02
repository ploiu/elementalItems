package elementalitems.items.tools;

import elementalitems.items.ElementalItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BaseTool extends ElementalItem {
	void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user);
}
