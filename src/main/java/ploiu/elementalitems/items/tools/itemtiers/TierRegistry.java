package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import ploiu.elementalitems.ElementalItems;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.combat.armor.material.*;
import ploiu.elementalitems.items.combat.weapons.swords.dual.DualTierBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TierRegistry {
	public static final IItemTier plainTier = new PlainItemTier();
	public static final IItemTier fireTier = new FireItemTier();
	public static final IItemTier iceTier = new IceItemTier();
	public static final IItemTier waterTier = new WaterItemTier();
	public static final IItemTier leafTier = new LeafItemTier();
	public static final IItemTier earthTier = new EarthItemTier();
	public static final IItemTier airTier = new AirItemTier();
	public static final IItemTier enderTier = new EnderItemTier();
	// armor materials
	public static final IArmorMaterial plainArmorMaterial = new PlainArmorMaterial();
	public static final IArmorMaterial fireArmorMaterial = new FireArmorMaterial();
	public static final IArmorMaterial iceArmorMaterial = new IceArmorMaterial();
	public static final IArmorMaterial waterArmorMaterial = new WaterArmorMaterial();
	public static final IArmorMaterial leafArmorMaterial = new LeafArmorMaterial();
	public static final IArmorMaterial earthArmorMaterial = new EarthArmorMaterial();
	public static final IArmorMaterial airArmorMaterial = new AirArmorMaterial();
	public static final IArmorMaterial enderArmorMaterial = new EnderArmorMaterial();

	private static final Map<String, IItemTier> dualItemTiers = new HashMap<>();

	static {
		// generate a list of all the dualItem tiers
		/*
		 * use a for loop to iterate through the values, and have a staggered, inner loop to
		 * get unique and non-same pairs (e.g. no fire+fire pairs and there won't be a fire+ice and an ice+fire pair)
		 */
		ElementalTypes[] values = ElementalTypes.values();
		// avoid creating new variables in loops since there's a potential for memory leaks afaik
		ElementalTypes[] currentPair = new ElementalTypes[2];
		for(int i = 1; i < values.length; i++) {
			// more performant than putting this in the inner loop
			currentPair[0] = values[i];
			for(int j = i + 1; j < values.length; j++) {
				// set and sort the currentPair and create a dualMaterial from them
				currentPair[1] = values[j];
				Arrays.sort(currentPair);
				final String typeKey = String.format("%s_%s", currentPair[0], currentPair[1]);
				try {
					final IItemTier dualTier = DualTierBuilder.buildTier(currentPair[0], currentPair[1]);
					dualItemTiers.put(typeKey, dualTier);
				} catch(IllegalArgumentException e) {
					ElementalItems.LOGGER.error("Bad matchup when creating dual tiers!");
				}
			}
		}
	}

	public static IItemTier getDualTierForTypes(ElementalTypes first, ElementalTypes second) {
		// type order matters, so make sure it's sorted
		ElementalTypes[] orderedTypes = new ElementalTypes[]{first, second};
		Arrays.sort(orderedTypes);
		String key = String.format("%s_%s", orderedTypes[0], orderedTypes[1]);
		return dualItemTiers.get(key);
	}
}
