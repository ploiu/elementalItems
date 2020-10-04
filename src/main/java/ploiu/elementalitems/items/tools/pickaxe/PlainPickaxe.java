package ploiu.elementalitems.items.tools.pickaxe;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class PlainPickaxe extends BasePickaxe {
	
	public PlainPickaxe() {
		super(ElementalTypes.PLAIN);
	}
	
	@Override
	public void applyEffect(ItemStack stack, World world, BlockState blockState, BlockPos blockPos, LivingEntity user) {
		
	}
}
