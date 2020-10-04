package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IItemTier;

public class TierRegistry {
	public static final IItemTier plainTier = new PlainItemTier();
	public static final IItemTier fireTier = new FireItemTier();
	public static final IItemTier iceTier = new IceItemTier();
	public static final IItemTier waterTier = new WaterItemTier();
	public static final IItemTier leafTier = new LeafItemTier();
	public static final IItemTier earthTier = new EarthItemTier();
	public static final IItemTier airTier = new AirItemTier();
	public static final IItemTier enderTier = new EnderItemTier();
}
