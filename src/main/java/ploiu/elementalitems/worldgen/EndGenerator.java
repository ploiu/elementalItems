package ploiu.elementalitems.worldgen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import ploiu.elementalitems.blocks.ElementalItemsBlockRegistry;
import ploiu.elementalitems.blocks.ores.BaseOre;
import ploiu.elementalitems.worldgen.features.ElementalItemsFeatureRegistry;
import ploiu.elementalitems.worldgen.features.ExpandedOreFeatureConfig;

public class EndGenerator {
	public static void setupEndGeneration() {
		BaseOre ore = ElementalItemsBlockRegistry.enderCrystalOre;
		// for each biome in the end, register this block to generate
		ore.getBiomesToGenerateIn().forEach(biome -> {
			biome.addFeature(
					GenerationStage.Decoration.UNDERGROUND_ORES,
					Biome.createDecoratedFeature(
							ElementalItemsFeatureRegistry.expandedOreFeature,
							new ExpandedOreFeatureConfig(ExpandedOreFeatureConfig.FillerBlockType.ENDER_CRYSTAL, ore.getDefaultState(), ore.getMaxVeinSize()),
							Placement.COUNT_RANGE,
							new CountRangeConfig(10, ore.getMinYGeneration(), 0, ore.getMaxYGeneration())
					)
			);
		});
	}
}
