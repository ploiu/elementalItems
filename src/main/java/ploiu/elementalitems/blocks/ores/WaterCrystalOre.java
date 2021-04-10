package ploiu.elementalitems.blocks.ores;

import net.minecraft.block.Blocks;
import ploiu.elementalitems.ElementalTypes;

import java.util.Arrays;
import java.util.HashSet;

public class WaterCrystalOre extends BaseOre {
	public WaterCrystalOre() {
		super(ElementalTypes.WATER);
		this.blocksThisCanGenerateOver = Arrays.asList(Blocks.STONE, Blocks.SAND, Blocks.GRAVEL, Blocks.COBBLESTONE);
		this.biomesToGenerateIn = new HashSet<>();/*this.getBiomesToGenerateInFromTypes(BiomeDictionary.Type.WATER, BiomeDictionary.Type.WET);*/
	}
}
