package ploiu.elementalitems.util;

import net.minecraft.item.BlockItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.blocks.BaseBlock;
import ploiu.elementalitems.blocks.ElementalItemsBlockRegistry;
import ploiu.elementalitems.entity.arrow.*;
import ploiu.elementalitems.items.BaseCrystal;
import ploiu.elementalitems.items.ElementalItem;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.items.combat.weapons.arrows.ElementalArrow;
import ploiu.elementalitems.items.tools.itemtiers.TierRegistry;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ElementalUtils {

	private ElementalUtils() {
	}

	/**
	 * gets and returns a passed {@link IItemTier ToolMaterial} whose {@link ElementalTypes} is the same as our type
	 *
	 * @param type the ElementalType we want to get the tool material for
	 * @return the ToolMaterial associated with the passed type
	 */
	public static IItemTier getToolMaterialFromElementalType(@Nonnull ElementalTypes type) {
		switch(type) {
			case FIRE:
				return TierRegistry.fireTier;
			case WATER:
				return TierRegistry.waterTier;
			case EARTH:
				return TierRegistry.earthTier;
			case AIR:
				return TierRegistry.airTier;
			case ICE:
				return TierRegistry.iceTier;
			case ENDER:
				return TierRegistry.enderTier;
			case LEAF:
				return TierRegistry.leafTier;
			case PLAIN:
			default:
				return TierRegistry.plainTier;
		}
	}

	/**
	 * gets the associated {@link IArmorMaterial ArmorMaterial} from the passed {@link ElementalTypes}
	 *
	 * @param type the type we want the armor material from
	 * @return the ArmorMaterial with the same type as our passed type
	 */
	public static IArmorMaterial getArmorMaterialFromElementalType(@Nonnull ElementalTypes type) {
		switch(type) {
			case FIRE:
				return TierRegistry.fireArmorMaterial;
			case WATER:
				return TierRegistry.waterArmorMaterial;
			case EARTH:
				return TierRegistry.earthArmorMaterial;
			case AIR:
				return TierRegistry.airArmorMaterial;
			case ICE:
				return TierRegistry.iceArmorMaterial;
			case ENDER:
				return TierRegistry.enderArmorMaterial;
			case LEAF:
				return TierRegistry.leafArmorMaterial;
			case PLAIN:
			default:
				return TierRegistry.plainArmorMaterial;
		}
	}

	/**
	 * returns the {@link BaseCrystal} associated with the passed {@link ElementalTypes}
	 *
	 * @param type the type we want to get the crystal for
	 * @return the associated BaseCrystal for the passed type
	 */
	public static BaseCrystal getCrystalForElementalType(@Nonnull ElementalTypes type) {
		switch(type) {
			case PLAIN:
				return ElementalItemsItemRegistry.plainCrystal;
			case FIRE:
				return ElementalItemsItemRegistry.fireCrystal;
			case ICE:
				return ElementalItemsItemRegistry.iceCrystal;
			case WATER:
				return ElementalItemsItemRegistry.waterCrystal;
			case LEAF:
				return ElementalItemsItemRegistry.leafCrystal;
			case EARTH:
				return ElementalItemsItemRegistry.earthCrystal;
			case AIR:
				return ElementalItemsItemRegistry.airCrystal;
			case ENDER:
				return ElementalItemsItemRegistry.enderCrystal;
		}
		// this shouldn't happen
		throw new IllegalArgumentException("There is not a crystal associated with type " + type.toString());
	}

	/**
	 * Gets the particles that should be shown when a sword hits an entity
	 *
	 * @param types the {@link ElementalTypes} we want to get the particles for
	 * @return a Map that may contain particles to be used by the calling method
	 * @see BaseSword#spawnAttackParticles
	 */
	@Nonnull
	@SuppressWarnings("rawtypes")
	public static Map<IParticleData, Integer> getParticlesForElementalType(ElementalTypes types) {
		// the map of particles
		Map<IParticleData, Integer> particlesMap = new HashMap<>();
		switch(types) {
			case PLAIN:
				// no op
				break;
			case FIRE:
				particlesMap.put(ParticleTypes.FLAME, 50);
				break;
			case ICE:
				particlesMap.put(ParticleTypes.ITEM_SNOWBALL, 50);
				break;
			case WATER:
				particlesMap.put(ParticleTypes.SPLASH, 50);
				break;
			case LEAF:
				particlesMap.put(ParticleTypes.TOTEM_OF_UNDYING, 30);
				break;
			case EARTH:
				particlesMap.put(ParticleTypes.MYCELIUM, 50);
				break;
			case AIR:
				particlesMap.put(ParticleTypes.CLOUD, 100);
				break;
			case ENDER:
				particlesMap.put(ParticleTypes.PORTAL, 50);
				break;
		}
		return particlesMap;
	}

	@Nonnull
	@SuppressWarnings("rawtypes")
	public static Map<IParticleData, Integer> getMixedParticlesForElementalTypes(ElementalTypes type1, ElementalTypes type2) {
		Map<IParticleData, Integer> mixedParticles = new HashMap<>();
		switch(type1) {
			case PLAIN:
				// no op
				break;
			case FIRE:
				// if type2 is water, make steam
				if(type2 == ElementalTypes.WATER) {
					mixedParticles.put(ParticleTypes.CLOUD, 50);
					mixedParticles.put(ParticleTypes.LARGE_SMOKE, 30);
				}
				break;
			case ICE:
				break;
			case WATER:
				break;
			case LEAF:
				break;
			case EARTH:
				break;
			case AIR:
				break;
			case ENDER:
				break;
		}
		return mixedParticles;
	}

	public static BlockItem getCrystalBlockItemForElementalType(ElementalTypes type) {
		switch(type) {
			case PLAIN:
				return ElementalItemsBlockRegistry.plainCrystalBlockItem;
			case FIRE:
				return ElementalItemsBlockRegistry.fireCrystalBlockItem;
			case ICE:
				return ElementalItemsBlockRegistry.iceCrystalBlockItem;
			case WATER:
				return ElementalItemsBlockRegistry.waterCrystalBlockItem;
			case LEAF:
				return ElementalItemsBlockRegistry.leafCrystalBlockItem;
			case EARTH:
				return ElementalItemsBlockRegistry.earthCrystalBlockItem;
			case AIR:
				return ElementalItemsBlockRegistry.airCrystalBlockItem;
			case ENDER:
				return ElementalItemsBlockRegistry.enderCrystalBlockItem;
			default:
				return ElementalItemsBlockRegistry.plainCrystalBlockItem;
		}
	}

	public static ElementalArrow getArrowItemForType(ElementalTypes type) {
		switch(type) {
			case PLAIN:
				return ElementalItemsItemRegistry.plainArrow;
			case FIRE:
				return ElementalItemsItemRegistry.fireArrow;
			case ICE:
				return ElementalItemsItemRegistry.iceArrow;
			case WATER:
				return ElementalItemsItemRegistry.waterArrow;
			case LEAF:
				return ElementalItemsItemRegistry.leafArrow;
			case EARTH:
				return ElementalItemsItemRegistry.earthArrow;
			case AIR:
				return ElementalItemsItemRegistry.airArrow;
			case ENDER:
				return ElementalItemsItemRegistry.enderArrow;
			default:
				return ElementalItemsItemRegistry.plainArrow;
		}
	}
}