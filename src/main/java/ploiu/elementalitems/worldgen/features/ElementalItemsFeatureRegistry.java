package ploiu.elementalitems.worldgen.features;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.RegistryEvent;
import ploiu.elementalitems.blocks.ElementalItemsBlockRegistry;

@SuppressWarnings("unchecked")
public class ElementalItemsFeatureRegistry {
	public static final ConfiguredFeature<?, ?> CRYSTAL_PLAIN_FEATURE = register("ore_crystal_plain",
			Feature.ORE.withConfiguration(
					new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, ElementalItemsBlockRegistry.plainCrystalOre.getDefaultState(), 9))
					.range(64)
					.square()
					.func_242731_b(20));

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
	}
}
