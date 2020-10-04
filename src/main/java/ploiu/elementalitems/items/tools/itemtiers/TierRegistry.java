package ploiu.elementalitems.items.tools.itemtiers;

import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import ploiu.elementalitems.items.combat.armor.material.*;

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
}
