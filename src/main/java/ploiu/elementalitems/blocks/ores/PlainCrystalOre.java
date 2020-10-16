package ploiu.elementalitems.blocks.ores;

import net.minecraftforge.common.BiomeDictionary;
import ploiu.elementalitems.ElementalTypes;

import java.util.Arrays;

import static net.minecraft.block.Blocks.*;
import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class PlainCrystalOre extends BaseOre {

	public PlainCrystalOre() {
		super(ElementalTypes.PLAIN);
		this.blocksThisCanGenerateOver = Arrays.asList(
				STONE,
				COBBLESTONE,
				GRAVEL
		);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(
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
		);
		this.maxYGeneration = 50;
	}
}
