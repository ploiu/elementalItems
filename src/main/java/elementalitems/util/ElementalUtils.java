package elementalitems.util;

import elementalitems.ElementalTypes;
import elementalitems.items.ElementalMaterials;
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

	public Item.ToolMaterial getToolMaterialFromElementalType(ElementalTypes type) {
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

	public ItemArmor.ArmorMaterial getArmorMaterialFromElementalType(ElementalTypes type) {
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
