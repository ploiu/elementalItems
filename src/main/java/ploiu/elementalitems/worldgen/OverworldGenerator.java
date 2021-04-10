package ploiu.elementalitems.worldgen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.blocks.ores.BaseOre;
import ploiu.elementalitems.worldgen.features.ElementalItemsFeatureRegistry;
import ploiu.elementalitems.worldgen.features.ExpandedOreFeatureConfig;

import java.util.Arrays;
import java.util.Collection;
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
		// FIXME this is a temporary test for some crystals
		final Collection<Biome> biomes = plainCrystalOre.getBiomesToGenerateIn();
		BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
		for(Biome biome: biomes) {
			biomeBuilder.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ElementalItemsFeatureRegistry.CRYSTAL_PLAIN_FEATURE);
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
