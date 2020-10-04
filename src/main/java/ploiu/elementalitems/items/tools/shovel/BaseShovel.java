package ploiu.elementalitems.items.tools.shovel;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.items.ItemUtils;
import ploiu.elementalitems.items.tools.ElementalTool;

public abstract class BaseShovel extends ShovelItem implements ElementalTool {

	private final ElementalTypes type;

	public BaseShovel(ElementalTypes type) {
		super(ItemUtils.getItemTierFromType(type), 1, -2.8F, new Properties().group(ItemGroup.TOOLS));
		this.type = type;
		this.setRegistryName(String.format("shovel_%s", type.getTypeName()));
		ElementalItemsItemRegistry.addItem(this);
	}

	@Override
	public ElementalTypes getType() {
		return this.type;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		this.applyEffect(stack, worldIn, state, pos, entityLiving);
		return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
	}
}