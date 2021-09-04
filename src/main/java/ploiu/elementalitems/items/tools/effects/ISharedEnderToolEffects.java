package ploiu.elementalitems.items.tools.effects;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ploiu.elementalitems.util.EntityUtils;
import ploiu.elementalitems.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ISharedEnderToolEffects {
	default void teleportItemsToOwnerInventory(World world, BlockState blockState, BlockPos blockPos, LivingEntity user) {
		if(world instanceof ServerWorld && EntityUtils.isValidLivingEntity(user) && user instanceof PlayerEntity) {
			final List<ItemStack> blockDrops = new ArrayList<>();
			if(world.getBlockEntity(blockPos) instanceof IInventory && world.getBlockEntity(blockPos) != null) {
				blockDrops.addAll(Utils.getContainerItems((IInventory) world.getBlockEntity(blockPos)));
			}
			PlayerEntity player = (PlayerEntity) user;
			// get the block drops
			blockDrops.addAll(Block.getDrops(blockState, (ServerWorld) world, blockPos, null));
			// go through the user's inventory and add the block drops to their free slots
			List<ItemStack> didNotFitInInventory = blockDrops.stream().filter(drop -> !player.addItem(drop)).collect(Collectors.toList());
			// for the drops that did not fit, spawn them at the player's feet
			for(ItemStack stack : didNotFitInInventory) {
				ItemEntity droppedItem = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), stack);
				world.addFreshEntity(droppedItem);
			}
			// destroy the block so that we don't duplicate drops
			world.removeBlockEntity(blockPos);
			world.destroyBlock(blockPos, false);
		}
	}
}
