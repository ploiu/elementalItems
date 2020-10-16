package ploiu.elementalitems.blocks.ores;

import net.minecraft.block.Blocks;
import net.minecraftforge.common.BiomeDictionary;
import ploiu.elementalitems.ElementalTypes;

import java.util.Arrays;

import static net.minecraft.block.Blocks.*;

public class EarthCrystalOre extends BaseOre {
	public EarthCrystalOre() {
		super(ElementalTypes.EARTH);
		// override any type of hardened clay as well as stone
		this.blocksThisCanGenerateOver = Arrays.asList(
				STONE,
				COBBLESTONE,
				TERRACOTTA,
				BLACK_TERRACOTTA,
				WHITE_TERRACOTTA,
				ORANGE_TERRACOTTA,
				MAGENTA_TERRACOTTA,
				LIGHT_BLUE_TERRACOTTA,
				YELLOW_TERRACOTTA,
				LIME_TERRACOTTA,
				PINK_TERRACOTTA,
				GRAY_TERRACOTTA,
				LIGHT_GRAY_TERRACOTTA,
				CYAN_TERRACOTTA,
				PURPLE_TERRACOTTA,
				BLUE_TERRACOTTA,
				BROWN_TERRACOTTA,
				GREEN_TERRACOTTA,
				RED_TERRACOTTA,
				DIRT
		);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(
				BiomeDictionary.Type.MESA,
				BiomeDictionary.Type.MOUNTAIN
		);
		this.maxYGeneration = 100; // not too high up, but high enough to replace clay and stuff in mesas
	}
}
