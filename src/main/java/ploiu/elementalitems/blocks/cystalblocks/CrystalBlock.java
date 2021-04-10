package ploiu.elementalitems.blocks.cystalblocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.blocks.BaseBlock;

public class CrystalBlock extends BaseBlock {
	public CrystalBlock(ElementalTypes type) {
		super(type, Properties.create(Material.ROCK)
				            .hardnessAndResistance(5.0f, 6.0f)
				            .harvestLevel(2)
				            .harvestTool(ToolType.PICKAXE),
				String.format("block_crystal_%s", type.getTypeName()));
	}

	public CrystalBlock(ElementalTypes type, Properties properties) {
		super(type, properties, String.format("block_crystal_%s", type.getTypeName()));
	}

	
	
	// @Override TODO figure out alternative
	public boolean isBeaconBase(BlockState state, IWorldReader world, BlockPos pos, BlockPos beacon) {
		return true;
	}
}
