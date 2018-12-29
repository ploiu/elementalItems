package elementalitems.util;

import elementalitems.ElementalTypes;
import elementalitems.items.ElementalMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

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

	private static class SingletonHelper {
		private static final ElementalUtils instance = new ElementalUtils();
	}
}
