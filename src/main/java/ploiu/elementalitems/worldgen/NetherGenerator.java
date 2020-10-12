package ploiu.elementalitems.worldgen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import ploiu.elementalitems.blocks.ElementalItemsBlockRegistry;
import ploiu.elementalitems.blocks.ores.BaseOre;
import ploiu.elementalitems.worldgen.features.ExpandedOreFeatureConfig;

public class NetherGenerator {
	public static void setupNetherGenerator() {
		final BaseOre ore = ElementalItemsBlockRegistry.netherCrystalOre;
		Biomes.NETHER.addFeature(
				GenerationStage.Decoration.UNDERGROUND_ORES,
				Biome.createDecoratedFeature(
						Feature.ORE,
						new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ore.getDefaultState(), ore.getMaxVeinSize()),
						Placement.COUNT_RANGE,
						new CountRangeConfig(10, ore.getMinYGeneration(), 0, ore.getMaxYGeneration())
				)
		);
	}
}
