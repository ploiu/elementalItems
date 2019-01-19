package elementalitems.util;

import elementalitems.ElementalTypes;
import elementalitems.items.BaseCrystal;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import elementalitems.items.combat.swords.BaseSword;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.EnumParticleTypes;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ElementalUtils {

	private ElementalUtils() {
	}

	public static ElementalUtils getInstance() {
		return ElementalUtils.SingletonHelper.instance;
	}

	/**
	 * gets and returns a passed {@link Item.ToolMaterial ToolMaterial} whose {@link ElementalTypes} is the same as our type
	 *
	 * @param type the ElementalType we want to get the tool material for
	 * @return the ToolMaterial associated with the passed type
	 */
	public Item.ToolMaterial getToolMaterialFromElementalType(@Nonnull ElementalTypes type) {
		switch(type) {
			case FIRE:
				return ElementalMaterials.getInstance().TOOL_FIRE;
			case WATER:
				return ElementalMaterials.getInstance().TOOL_WATER;
			case EARTH:
				return ElementalMaterials.getInstance().TOOL_EARTH;
			case AIR:
				return ElementalMaterials.getInstance().TOOL_AIR;
			case ICE:
				return ElementalMaterials.getInstance().TOOL_ICE;
			case ENDER:
				return ElementalMaterials.getInstance().TOOL_ENDER;
			case LEAF:
				return ElementalMaterials.getInstance().TOOL_LEAF;
			case PLAIN:
			default:
				return ElementalMaterials.getInstance().TOOL_PLAIN;
		}
	}

	/**
	 * gets the associated {@link ItemArmor.ArmorMaterial ArmorMaterial} from the passed {@link ElementalTypes}
	 *
	 * @param type the type we want the armor material from
	 * @return the ArmorMaterial with the same type as our passed type
	 */
	public ItemArmor.ArmorMaterial getArmorMaterialFromElementalType(@Nonnull ElementalTypes type) {
		switch(type) {
			case FIRE:
				return ElementalMaterials.getInstance().ARMOR_FIRE;
			case WATER:
				return ElementalMaterials.getInstance().ARMOR_WATER;
			case EARTH:
				return ElementalMaterials.getInstance().ARMOR_EARTH;
			case AIR:
				return ElementalMaterials.getInstance().ARMOR_AIR;
			case ICE:
				return ElementalMaterials.getInstance().ARMOR_ICE;
			case ENDER:
				return ElementalMaterials.getInstance().ARMOR_ENDER;
			case LEAF:
				return ElementalMaterials.getInstance().ARMOR_LEAF;
			case PLAIN:
			default:
				return ElementalMaterials.getInstance().ARMOR_PLAIN;
		}
	}

	/**
	 * returns the {@link BaseCrystal} associated with the passed {@link ElementalTypes}
	 *
	 * @param type the type we want to get the crystal for
	 * @return the assocated BaseCrystal for the passed type
	 */
	public BaseCrystal getCrystalForElementalType(@Nonnull ElementalTypes type) {
		switch(type) {
			case PLAIN:
				return (BaseCrystal) ItemHandler.plainCrystal;
			case FIRE:
				return (BaseCrystal) ItemHandler.fireCrystal;
			case ICE:
				return (BaseCrystal) ItemHandler.iceCrystal;
			case WATER:
				return (BaseCrystal) ItemHandler.waterCrystal;
			case LEAF:
				return (BaseCrystal) ItemHandler.leafCrystal;
			case EARTH:
				return (BaseCrystal) ItemHandler.earthCrystal;
			case AIR:
				return (BaseCrystal) ItemHandler.airCrystal;
			case ENDER:
				return (BaseCrystal) ItemHandler.enderCrystal;
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
	public Map<EnumParticleTypes, Integer> getParticlesForElementalType(ElementalTypes types) {
		// the map of particles
		Map<EnumParticleTypes, Integer> particlesMap = new HashMap<>();
		switch(types) {
			case PLAIN:
				// no op
				break;
			case FIRE:
				particlesMap.put(EnumParticleTypes.FLAME, 50);
				break;
			case ICE:
				particlesMap.put(EnumParticleTypes.SNOW_SHOVEL, 50);
				particlesMap.put(EnumParticleTypes.SNOWBALL, 20);
				break;
			case WATER:
				particlesMap.put(EnumParticleTypes.WATER_SPLASH, 50);
				break;
			case LEAF:
				particlesMap.put(EnumParticleTypes.TOTEM, 30);
				break;
			case EARTH:
				particlesMap.put(EnumParticleTypes.TOWN_AURA, 50);
				break;
			case AIR:
				particlesMap.put(EnumParticleTypes.CLOUD, 100);
				break;
			case ENDER:
				particlesMap.put(EnumParticleTypes.PORTAL, 50);
				break;
		}
		return particlesMap;
	}

	@Nonnull
	public Map<EnumParticleTypes, Integer> getMixedParticlesForElementalTypes(ElementalTypes type1, ElementalTypes type2) {
		Map<EnumParticleTypes, Integer> mixedParticles = new HashMap<>();
		switch(type1) {
			case PLAIN:
				// no op
				break;
			case FIRE:
				// if type2 is water, make steam
				if(type2 == ElementalTypes.WATER) {
					mixedParticles.put(EnumParticleTypes.CLOUD, 50);
					mixedParticles.put(EnumParticleTypes.SMOKE_LARGE, 30);
				}
				break;
			case ICE:
				if(type2 == ElementalTypes.WATER) {
					// going to use packed ice particles
					mixedParticles.put(EnumParticleTypes.BLOCK_DUST, 80);
				}
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

	private static class SingletonHelper {
		private static final ElementalUtils instance = new ElementalUtils();
	}
}
