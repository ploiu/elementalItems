package ploiu.elementalitems.blocks.cystalblocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.common.ToolType;
import ploiu.elementalitems.ElementalTypes;

public class IceCrystalBlock extends CrystalBlock {
	public IceCrystalBlock() {
		super(ElementalTypes.ICE, Properties.create(Material.ROCK)
				                          .hardnessAndResistance(5.0f, 6.0f)
				                          .harvestLevel(2)
				                          .harvestTool(ToolType.PICKAXE)
				                          .slipperiness(0.98f));
	}

	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
