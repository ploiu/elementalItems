package ploiu.elementalitems.blocks.ores;

import ploiu.elementalitems.ElementalTypes;

import java.util.Arrays;
import java.util.HashSet;

import static net.minecraft.block.Blocks.*;

public class PlainCrystalOre extends BaseOre {

	public PlainCrystalOre() {
		super(ElementalTypes.PLAIN);
		this.blocksThisCanGenerateOver = Arrays.asList(
				STONE,
				COBBLESTONE,
				GRAVEL
		);
		this.biomesToGenerateIn = new HashSet<>(); /*this.getBiomesToGenerateInFromTypes(
				WET,
				DRY,
				HOT,
				COLD,
				PLAINS,
				OCEAN,
				MOUNTAIN,
				FOREST,
				RIVER,
				SNOWY,
				RARE,
				BEACH,
				SANDY,
				SAVANNA
		);*/
		this.maxYGeneration = 50;
	}
}
