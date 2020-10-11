package ploiu.elementalitems.worldgen.features;

import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;

@SuppressWarnings("unchecked")
public class ElementalItemsFeatureRegistry {
	public static Feature<ExpandedOreFeatureConfig> expandedOreFeature = (Feature<ExpandedOreFeatureConfig>) new ExpandedOreFeature(ExpandedOreFeatureConfig::deserialize).setRegistryName("expanded_ore_feature");

	public static void registerFeatures(final RegistryEvent.Register<Feature<?>> event) {
		event.getRegistry().registerAll(
				expandedOreFeature
		);
	}
}
