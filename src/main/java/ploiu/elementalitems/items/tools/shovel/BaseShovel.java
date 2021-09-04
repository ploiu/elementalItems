package ploiu.elementalitems.items.tools.shovel;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.items.tools.ElementalTool;
import ploiu.elementalitems.util.ItemUtils;

public abstract class BaseShovel extends ShovelItem implements ElementalTool {

	private final ElementalTypes type;

	public BaseShovel(ElementalTypes type) {
		super(ItemUtils.getItemTierFromType(type), 1, -2.8F, new Properties().tab(ItemGroup.TAB_TOOLS));
		this.type = type;
		this.setRegistryName(String.format("shovel_%s", type.getTypeName()));
		ElementalItemsItemRegistry.addItem(this);
	}
	
	protected BaseShovel(ElementalTypes type, float attackSpeed) {
		super(ItemUtils.getItemTierFromType(type), 1, attackSpeed, new Properties().tab(ItemGroup.TAB_TOOLS));
		this.type = type;
		this.setRegistryName(String.format("shovel_%s", type.getTypeName()));
		ElementalItemsItemRegistry.addItem(this);
	}

	@Override
	public ElementalTypes getType() {
		return this.type;
	}

	@Override
	public boolean mineBlock(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		this.applyEffect(stack, worldIn, state, pos, entityLiving);
		return super.mineBlock(stack, worldIn, state, pos, entityLiving);
	}
}
