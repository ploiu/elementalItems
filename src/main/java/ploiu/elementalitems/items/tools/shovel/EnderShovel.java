package ploiu.elementalitems.items.tools.shovel;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.tools.effects.ISharedEnderToolEffects;

public class EnderShovel extends BaseShovel implements ISharedEnderToolEffects {

	public EnderShovel() {
		super(ElementalTypes.ENDER);
	}

	@Override
	public void applyEffect(ItemStack stack, World world, BlockState blockState, BlockPos blockPos, LivingEntity user) {
		if(blockState.getBlock().getHarvestTool(blockState) == ToolType.SHOVEL) {
			this.teleportItemsToOwnerInventory(world, blockState, blockPos, user);
		}
	}
}
