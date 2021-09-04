package ploiu.elementalitems.items.tools.pickaxe;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.tools.effects.ISharedEnderToolEffects;

public class EnderPickaxe extends BasePickaxe implements ISharedEnderToolEffects {

	public EnderPickaxe() {
		super(ElementalTypes.ENDER);
	}

	@Override
	public void applyEffect(ItemStack stack, World world, BlockState blockState, BlockPos blockPos, LivingEntity user) {
		if(this.canHarvestBlock(stack, blockState)) {
			this.teleportItemsToOwnerInventory(world, blockState, blockPos, user);
		}
	}
}
