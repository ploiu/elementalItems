package ploiu.elementalitems.items.tools.axe;

import net.minecraft.block.BannerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.tools.effects.ISharedEnderToolEffects;

public class EnderAxe extends BaseAxe implements ISharedEnderToolEffects {

	public EnderAxe() {
		super(ElementalTypes.ENDER);
	}

	@Override
	public void applyEffect(ItemStack stack, World world, BlockState blockState, BlockPos blockPos, LivingEntity user) {
		if(blockState.getBlock().getHarvestTool(blockState) == ToolType.AXE) {
			this.teleportItemsToOwnerInventory(world, blockState, blockPos, user);
		}
	}
}
