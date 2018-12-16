package elementalitems.sharedeffects.items;

import elementalitems.util.EntityUtils;
import elementalitems.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;

public interface ISharedFireEffect {

	default void applyFireEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user) {
		if(!world.isRemote) {
			// get the item to drop based on the smelting map
			ItemStack smeltedItemStack = Utils.getInstance().getSmeltedResultWithCorrectMeta(state);
			// make sure the result actually is something
			if(!smeltedItemStack.getItem().equals(Items.AIR)) {
				// destroy the block and try to drop the smelted item where the block was
				world.destroyBlock(position, false);
				EntityUtils.getInstance().dropItemsInWorld(world, user, Collections.singletonList(smeltedItemStack));
			}
		}
	}
}
