package ploiu.elementalitems.items.tools.shovel;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.util.EntityUtils;

public class LeafShovel extends BaseShovel {

	public LeafShovel() {
		super(ElementalTypes.LEAF);
	}

	@Override
	public void applyEffect(ItemStack stack, World world, BlockState blockState, BlockPos blockPos, LivingEntity user) {
		EntityUtils.spawnXpOrb(world, blockPos, 2);
	}
}
