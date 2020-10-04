package ploiu.elementalitems.items.tools;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItem;

public interface ElementalTool extends ElementalItem {
	void applyEffect(final ItemStack stack, final World world, final BlockState blockState, final BlockPos blockPos, final LivingEntity user);
}
