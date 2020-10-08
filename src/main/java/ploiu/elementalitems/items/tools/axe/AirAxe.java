package ploiu.elementalitems.items.tools.axe;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class AirAxe extends BaseAxe {
	
	public AirAxe() {
		super(ElementalTypes.AIR, 2f);
	}
	
	@Override
	public void applyEffect(ItemStack stack, World world, BlockState blockState, BlockPos blockPos, LivingEntity user) {
		
	}
}
