package ploiu.elementalitems.blocks.cystalblocks;

import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.blocks.BaseBlock;

public class FireCrystalBlock extends CrystalBlock {
	public FireCrystalBlock() {
		super(ElementalTypes.FIRE, Properties.of(Material.STONE)
				                           .strength(5.0f, 6.0f)
				                           .harvestLevel(2)
				                           .harvestTool(ToolType.PICKAXE)
				                           .lightLevel(state -> 15)
				                           .requiresCorrectToolForDrops());
	}
}
