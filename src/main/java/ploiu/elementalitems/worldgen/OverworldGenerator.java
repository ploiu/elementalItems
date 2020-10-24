package ploiu.elementalitems.worldgen;

import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.blocks.ores.BaseOre;
import ploiu.elementalitems.worldgen.features.ElementalItemsFeatureRegistry;
import ploiu.elementalitems.worldgen.features.ExpandedOreFeatureConfig;

import java.util.Arrays;
import java.util.List;

import static ploiu.elementalitems.blocks.ElementalItemsBlockRegistry.*;

public class OverworldGenerator {

	public static void setupOverworldGeneration() {
		// get all the ores for the overworld
		final List<BaseOre> ores = Arrays.asList(
				plainCrystalOre,
				fireCrystalOre,
				iceCrystalOre,
				waterCrystalOre,
				leafCrystalOre,
				earthCrystalOre,
				airCrystalOre
		);
		// for each base ore, register it with the biome to generate
		for(BaseOre ore : ores) {
			ore.getBiomesToGenerateIn().forEach(biome -> {
				// for each block that can be replaced by the ore, add a feature to the biome
				ore.getBlocksToGenerateOver().forEach(block -> {
					// get the type of blocks to replace with this ore
					if(iceCrystalOre.equals(ore)) {
						biome.addFeature(
								GenerationStage.Decoration.UNDERGROUND_ORES,
								ElementalItemsFeatureRegistry.iceOreFeature.withConfiguration(
										new ExpandedOreFeatureConfig(getFillerBlockForElementalType(ore.getType()), ore.getDefaultState(), ore.getMaxVeinSize())
								).withPlacement(
										Placement.COUNT_RANGE.configure(new CountRangeConfig(ore.getMaxVeinSize(), ore.getMinYGeneration(), 0, ore.getMaxYGeneration()))
								)
						);
					} else {
						biome.addFeature(
								GenerationStage.Decoration.UNDERGROUND_ORES,
								ElementalItemsFeatureRegistry.expandedOreFeature.withConfiguration(
										new ExpandedOreFeatureConfig(getFillerBlockForElementalType(ore.getType()), ore.getDefaultState(), ore.getMaxVeinSize())
								).withPlacement(
										Placement.COUNT_RANGE.configure(new CountRangeConfig(ore.getMaxVeinSize(), ore.getMinYGeneration(), 0, ore.getMaxYGeneration()))
								)
						);
					}
				});
			});
		}
	}

	private static ExpandedOreFeatureConfig.FillerBlockType getFillerBlockForElementalType(ElementalTypes type) {
		switch(type) {
			case PLAIN:
			case FIRE:
			case WATER:
			case LEAF:
			case AIR:
				return ExpandedOreFeatureConfig.FillerBlockType.NATURAL_STONE;
			case ICE:
				return ExpandedOreFeatureConfig.FillerBlockType.ICE_CRYSTAL;
			case EARTH:
				return ExpandedOreFeatureConfig.FillerBlockType.EARTH_CRYSTAL;
			case ENDER:
				return ExpandedOreFeatureConfig.FillerBlockType.ENDER_CRYSTAL;
			default:
				throw new IllegalArgumentException(String.format("The type [%s] is not a valid elemental type", type.getTypeName()));
		}
	}
}
