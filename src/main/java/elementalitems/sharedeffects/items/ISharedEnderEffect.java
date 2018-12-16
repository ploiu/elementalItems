package elementalitems.sharedeffects.items;

import elementalitems.util.EntityUtils;
import elementalitems.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;


public interface ISharedEnderEffect {

	/**
	 * called when an ender tool needs to applyEnderEffect the tool's effect; teleports the block to the player's inventory
	 * if possible, and if the block has its own inventory, attempts to teleport the items in that inventory to the player as well.
	 * If it's not possible, the block, and any of its items (if they exist), are dropped at the player's feet
	 *
	 * @param world    the world this is taking place in
	 * @param state    the state of the block being broken
	 * @param position the position the block is at
	 * @param user     the user of the tool
	 */
	default void applyEnderEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		// get the player's inventory
		if(user instanceof EntityPlayer && !world.isRemote) {
			final EntityPlayer playerUser = (EntityPlayer) user;
			// ILootContainer
			if(state.getBlock().hasTileEntity(state) && world.getTileEntity(position) instanceof ILockableContainer) {
				ILockableContainer container = (ILockableContainer) world.getTileEntity(position);
				if(container != null) {
					List<ItemStack> blockDrops = new ArrayList<>(Utils.getInstance().getContainerContents(container, true));
					// add the block to toAddToInventory
					blockDrops.add(new ItemStack(Item.getItemFromBlock(state.getBlock()), 1, state.getBlock().damageDropped(state)));
					List<ItemStack> remainingItems = EntityUtils.getInstance().addItemsToPlayerInventory(playerUser, blockDrops);
					EntityUtils.getInstance().dropItemsInWorld(world, playerUser, remainingItems);
					// remove the tile entity
					world.removeTileEntity(position);
				}
			} else {
				// it's not a container, so just get the block
				NonNullList<ItemStack> drops = NonNullList.create();
				state.getBlock().getDrops(drops, world, position, state, 0);
				List<ItemStack> remainingItems = EntityUtils.getInstance().addItemsToPlayerInventory(playerUser, drops);
				EntityUtils.getInstance().dropItemsInWorld(world, playerUser, remainingItems);
			}
			// delete the block
			world.destroyBlock(position, false);
		}

	}
}
