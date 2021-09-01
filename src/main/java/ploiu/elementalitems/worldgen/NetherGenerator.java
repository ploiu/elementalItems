package ploiu.elementalitems.worldgen;

import net.minecraft.world.biome.Biomes;
import ploiu.elementalitems.blocks.ElementalItemsBlockRegistry;
import ploiu.elementalitems.blocks.ores.BaseOre;

public class NetherGenerator {
	public static void setupNetherGenerator() {
		final BaseOre ore = ElementalItemsBlockRegistry.netherCrystalOre;
		// Biomes.NETHER.addFeature(
		// 		GenerationStage.Decoration.UNDERGROUND_ORES,
		// 		ElementalItemsFeatureRegistry.expandedOreFeature.withConfiguration(
		// 				new ExpandedOreFeatureConfig(ExpandedOreFeatureConfig.FillerBlockType.NETHERRACK, ore.getDefaultState(), ore.getMaxVeinSize())
		// 		).withPlacement(
		// 				Placement.COUNT_RANGE.configure(new CountRangeConfig(10, ore.getMinYGeneration(), 0, ore.getMaxYGeneration()))
		// 		)
		// );
	}
}
