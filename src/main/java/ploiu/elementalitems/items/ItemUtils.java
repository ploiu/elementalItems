package ploiu.elementalitems.items;

import net.minecraft.item.IItemTier;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.tools.itemtiers.TierRegistry;

import javax.annotation.Nonnull;
import javax.rmi.CORBA.Tie;

import static ploiu.elementalitems.items.tools.itemtiers.TierRegistry.fireTier;

public class ItemUtils {
	private ItemUtils() {
	}

	public static IItemTier getItemTierFromType(@Nonnull ElementalTypes type) {
		switch(type) {
			case FIRE:
				return TierRegistry.fireTier;
			case ICE:
				return TierRegistry.iceTier;
			case WATER:
				return TierRegistry.waterTier;
			case LEAF:
				return TierRegistry.leafTier;
			case EARTH:
				return TierRegistry.earthTier;
			case AIR:
				return TierRegistry.airTier;
			case ENDER:
				return TierRegistry.enderTier;
			case PLAIN:
			default:
				return TierRegistry.plainTier;
		}
	}
}
