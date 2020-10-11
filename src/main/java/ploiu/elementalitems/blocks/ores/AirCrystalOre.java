package ploiu.elementalitems.blocks.ores;

import net.minecraft.block.Blocks;
import net.minecraftforge.common.BiomeDictionary;
import ploiu.elementalitems.ElementalTypes;

import java.util.Arrays;

public class AirCrystalOre extends BaseOre {
	public AirCrystalOre() {
		super(ElementalTypes.AIR);

		this.blocksThisCanGenerateOver = Arrays.asList(Blocks.STONE, Blocks.COBBLESTONE);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(
				BiomeDictionary.Type.WET,
				BiomeDictionary.Type.DRY,
				BiomeDictionary.Type.HOT,
				BiomeDictionary.Type.COLD,
				BiomeDictionary.Type.PLAINS,
				BiomeDictionary.Type.OCEAN,
				BiomeDictionary.Type.MOUNTAIN,
				BiomeDictionary.Type.FOREST,
				BiomeDictionary.Type.RIVER,
				BiomeDictionary.Type.SNOWY,
				BiomeDictionary.Type.RARE,
				BiomeDictionary.Type.BEACH,
				BiomeDictionary.Type.SANDY,
				BiomeDictionary.Type.SAVANNA
		);
		// minimum generation height should be high up
		this.minYGeneration = 120;
		this.maxYGeneration = 255;
	}
}
