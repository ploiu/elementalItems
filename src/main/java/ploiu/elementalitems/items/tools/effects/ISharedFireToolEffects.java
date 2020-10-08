package ploiu.elementalitems.items.tools.effects;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ploiu.elementalitems.util.Utils;

import java.util.List;
import java.util.stream.Collectors;

public interface ISharedFireToolEffects {
	default void smeltItem(World world, BlockState blockState, BlockPos blockPos) {
		// if we're on a server world, we can get the block drops
		if(world instanceof ServerWorld) {
			List<ItemStack> drops = Block.getDrops(blockState, (ServerWorld) world, blockPos, null);
			List<ItemStack> smeltedOutput = drops.stream().map(Utils::getSmeltingResult).collect(Collectors.toList());
			// destroy the block to prevent duplicate drops
			world.removeBlock(blockPos, false);
			// for each output item stack, drop it in place of the block
			for(int i = 0; i < smeltedOutput.size(); i++) {
				try {
					ItemStack currentItem = smeltedOutput.get(i);
					// if the item is null, then there's no smelting output and we should drop the original item
					currentItem = currentItem == null ? drops.get(i) : currentItem;
					world.addEntity(new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), currentItem));
				} catch(IndexOutOfBoundsException ignored) {
					// the lists should be the same exact size, this is more of a just in case so the game doesn't crash
				}
			}
		}
	}
}
