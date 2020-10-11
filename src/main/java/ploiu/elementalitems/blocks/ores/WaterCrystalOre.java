package ploiu.elementalitems.blocks.ores;

import net.minecraft.block.Blocks;
import net.minecraftforge.common.BiomeDictionary;
import ploiu.elementalitems.ElementalTypes;

import java.util.Arrays;

public class WaterCrystalOre extends BaseOre {
	public WaterCrystalOre() {
		super(ElementalTypes.WATER);
		this.blocksThisCanGenerateOver = Arrays.asList(Blocks.STONE, Blocks.SAND, Blocks.GRAVEL, Blocks.COBBLESTONE);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(BiomeDictionary.Type.WATER, BiomeDictionary.Type.WET);
	}
}
