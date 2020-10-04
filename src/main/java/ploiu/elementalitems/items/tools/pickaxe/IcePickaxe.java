package ploiu.elementalitems.items.tools.pickaxe;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class IcePickaxe extends BasePickaxe {
	
	public IcePickaxe() {
		super(ElementalTypes.ICE);
	}
	
	@Override
	public void applyEffect(ItemStack stack, World world, BlockState blockState, BlockPos blockPos, LivingEntity user) {
		
	}
}
