package ploiu.elementalitems.worldgen;

import net.minecraft.world.biome.BiomeGenerationSettings;
import ploiu.elementalitems.blocks.ElementalItemsBlockRegistry;
import ploiu.elementalitems.blocks.ores.BaseOre;

public class EndGenerator {
	public static void setupEndGeneration() {
		BaseOre ore = ElementalItemsBlockRegistry.enderCrystalOre;
		// for each biome in the end, register this block to generate
		BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder();
		// ore.getBiomesToGenerateIn().forEach(biome -> {
		// 	biomeBuilder.withFeature()
		// 	biome.addFeature(
		// 			GenerationStage.Decoration.UNDERGROUND_ORES,
		// 			ElementalItemsFeatureRegistry.expandedOreFeature.withConfiguration(
		// 					new ExpandedOreFeatureConfig(ExpandedOreFeatureConfig.FillerBlockType.ENDER_CRYSTAL, ore.getDefaultState(), ore.getMaxVeinSize())
		// 			).withPlacement(
		// 					Placement.COUNT_RANGE.configure(new CountRangeConfig(10, ore.getMinYGeneration(), 0, ore.getMaxYGeneration()))
		// 			)
		// 	);
		// });
	}
}
