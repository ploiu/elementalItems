package ploiu.elementalitems.items.tools.pickaxe;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.tools.effects.ISharedFireToolEffects;

public class FirePickaxe extends BasePickaxe implements ISharedFireToolEffects {

	public FirePickaxe() {
		super(ElementalTypes.FIRE);
	}

	@Override
	public void applyEffect(ItemStack stack, World world, BlockState blockState, BlockPos blockPos, LivingEntity user) {
		if(this.canHarvestBlock(stack, blockState)) {
			this.smeltItem(world, blockState, blockPos);
		}
	}
}
