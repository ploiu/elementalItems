package ploiu.elementalitems.items.tools.shovel;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class WaterShovel extends BaseShovel {

	public WaterShovel() {
		super(ElementalTypes.WATER);
	}

	@Override
	public void applyEffect(ItemStack stack, World world, BlockState blockState, BlockPos blockPos, LivingEntity user) {

	}
}
