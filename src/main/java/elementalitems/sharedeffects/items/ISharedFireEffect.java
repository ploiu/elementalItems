package elementalitems.sharedeffects.items;

import elementalitems.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ISharedFireEffect {

	default void applyFireEffect(World world, IBlockState state, BlockPos position) {
		// get the item to drop based on the smelting map
		ItemStack smeltedItemStack = Utils.getInstance().getSmeltedResultWithCorrectMeta(state);
		// make sure the result actually is something
		if(!smeltedItemStack.getItem().equals(Items.AIR)) {
			// destroy the block and try to drop the smelted item where the block was
			world.setBlockState(position, Blocks.AIR.getDefaultState());
			world.removeTileEntity(position);
			Block.spawnAsEntity(world, position, smeltedItemStack);
		}
	}
}
