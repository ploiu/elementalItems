package elementalitems.items;

import elementalitems.ElementalItems;
import elementalitems.ElementalTypes;
import elementalitems.util.ElementalUtils;
import net.minecraft.enchantment.EnchantmentKnockback;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.SoundEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.max;
import static net.minecraft.item.Item.ToolMaterial.*;
import static net.minecraftforge.common.util.EnumHelper.addArmorMaterial;
import static net.minecraftforge.common.util.EnumHelper.addToolMaterial;

/**
 * The properties for the elemental items. Their general properties are as follow:
 * <hr>
 * <h2>Fire</h2>
 * <ul>
 * <li>Average in everything, except for slightly-lower enchantability (cos have you <i>tried</i> to enchant fire?)</li>
 * <li>Tools will auto-smelt blocks they break</li>
 * <li>Weapons will set hit {@link EntityLiving EntityLiving} on fire TODO</li>
 * <li>armor will set {@link EntityLiving EntityLiving} on fire TODO</li>
 * <li>armor full set will provide perpetual fire resistance TODO</li>
 * <li>armor full set will allow wearer to move and see effortlessly in lava TODO</li>
 * </ul>
 * <br>
 * <hr>
 * <h2>Water</h2>
 * <ul>
 * <li>Average in everything, except for extremely-fast efficiency (50 cos rushing water will pummel everything)
 * </li>
 * <li>Weapons provide {@link EnchantmentKnockback Knockback V} TODO</li>
 * <li>Armor provides immunity to explosions (TODO remove the user from the list of entities affected by the
 * explosion)</li>
 * <li>Armor Full Set provided perpetual {@link MobEffects WATER_BREATHING} TODO</li>
 * <li>Armor full set provides clear vision in water and high meneuverability in water TODO</li>
 * </ul>
 */
public class ElementalMaterials {

	public final Map<String, Item.ToolMaterial> dualMaterials = new HashMap<>();

	// tool materials
	public Item.ToolMaterial TOOL_FIRE = null;
	public Item.ToolMaterial TOOL_ICE = null;
	public Item.ToolMaterial TOOL_WATER = null;
	public Item.ToolMaterial TOOL_LEAF = null;
	public Item.ToolMaterial TOOL_EARTH = null;
	public Item.ToolMaterial TOOL_AIR = null;
	public Item.ToolMaterial TOOL_ENDER = null;
	public Item.ToolMaterial TOOL_PLAIN = null;

	// armor materials
	public ItemArmor.ArmorMaterial ARMOR_FIRE = null;
	public ItemArmor.ArmorMaterial ARMOR_ICE = null;
	public ItemArmor.ArmorMaterial ARMOR_WATER = null;
	public ItemArmor.ArmorMaterial ARMOR_LEAF = null;
	public ItemArmor.ArmorMaterial ARMOR_EARTH = null;
	public ItemArmor.ArmorMaterial ARMOR_AIR = null;
	public ItemArmor.ArmorMaterial ARMOR_ENDER = null;
	public ItemArmor.ArmorMaterial ARMOR_PLAIN = null;

	private ElementalMaterials() {
	}

	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static ElementalMaterials getInstance() {
		return ElementalMaterials.SingletonHelper.instance;
	}

	/**
	 * called in {@link ElementalItems} during forge's init events to initialize and register our materials
	 */
	public void registerMaterials() {
		int BASE_DURABILITY = 2000;
		SoundEvent EQUIP_SOUND_EVENT = SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
		int BASE_ENCHANTABILITY = DIAMOND.getEnchantability();
		int BASE_HARVEST_LEVEL = 10;
		float BASE_ENTITY_DAMAGE = DIAMOND.getAttackDamage() + 2;
		float BASE_EFFICIENCY = DIAMOND.getEfficiency();
		int[] BASE_REDUCTION_AMOUNTS = new int[]{4, 7, 9, 4};
		int ICE_ENCHANTABILITY = 50;
		int EARTH_DURABILITY = 4000;
		int BASE_ARMOR_DURABILITY = 33;
		float BASE_TOUGHNESS = 2.0f;
		int EARTH_ARMOR_DURABILITY = 100;

		this.TOOL_FIRE = addToolMaterial("material_tool_fire", BASE_HARVEST_LEVEL, BASE_DURABILITY, BASE_EFFICIENCY, BASE_ENTITY_DAMAGE, STONE.getEnchantability());
		this.TOOL_ICE = addToolMaterial("material_tool_ice", BASE_HARVEST_LEVEL, 1000, BASE_EFFICIENCY, BASE_ENTITY_DAMAGE, ICE_ENCHANTABILITY);
		this.TOOL_WATER = addToolMaterial("material_tool_water", BASE_HARVEST_LEVEL, BASE_DURABILITY, 50.0f, BASE_ENTITY_DAMAGE, BASE_ENCHANTABILITY);
		this.TOOL_LEAF = addToolMaterial("material_tool_leaf", BASE_HARVEST_LEVEL, BASE_DURABILITY, BASE_EFFICIENCY, BASE_ENTITY_DAMAGE * 3, WOOD.getEnchantability());
		this.TOOL_EARTH = addToolMaterial("material_tool_earth", BASE_HARVEST_LEVEL, EARTH_DURABILITY, BASE_EFFICIENCY, BASE_ENTITY_DAMAGE, BASE_ENCHANTABILITY);
		this.TOOL_AIR = addToolMaterial("material_tool_air", BASE_HARVEST_LEVEL, BASE_DURABILITY, BASE_EFFICIENCY * 1.25f, BASE_ENTITY_DAMAGE, BASE_ENCHANTABILITY);
		this.TOOL_ENDER = addToolMaterial("material_tool_end", BASE_HARVEST_LEVEL, BASE_DURABILITY, BASE_EFFICIENCY, BASE_ENTITY_DAMAGE * 2, GOLD.getEnchantability());
		this.TOOL_PLAIN = addToolMaterial("material_tool_plain", BASE_HARVEST_LEVEL, BASE_DURABILITY, BASE_EFFICIENCY, BASE_ENTITY_DAMAGE, BASE_ENCHANTABILITY);
		// armor
		this.ARMOR_FIRE = addArmorMaterial("material_armor_fire", "elementalitems:armor_fire", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, STONE.getEnchantability(), EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_ICE = addArmorMaterial("material_armor_ice", "elementalitems:armor_ice", 20, BASE_REDUCTION_AMOUNTS, ICE_ENCHANTABILITY, EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_WATER = addArmorMaterial("material_armor_water", "elementalitems:armor_water", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, BASE_ENCHANTABILITY, EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_LEAF = addArmorMaterial("material_armor_leaf", "elementalitems:armor_leaf", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, WOOD.getEnchantability(), EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_EARTH = addArmorMaterial("material_armor_earth", "elementalitems:armor_earth", EARTH_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, BASE_ENCHANTABILITY, EQUIP_SOUND_EVENT, BASE_TOUGHNESS * 4);
		this.ARMOR_AIR = addArmorMaterial("material_armor_air", "elementalitems:armor_air", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, BASE_ENCHANTABILITY, EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_ENDER = addArmorMaterial("material_armor_ender", "elementalitems:armor_ender", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, GOLD.getEnchantability(), EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_PLAIN = addArmorMaterial("material_armor_plain", "elementalitems:armor_plain", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, BASE_ENCHANTABILITY, EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		// dual materials
		this.registerDualMaterials();
	}

	public void registerDualMaterials() {
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
				this.combineToolMaterials(currentPair[0], currentPair[1]);
			}
		}
	}

	/**
	 * takes 2 {@link ElementalTypes}, gets the ToolMaterial associated with each respective type, and creates a new
	 * material with the best attributes of the 2 input materials. This must be sorted
	 *
	 * @param type1 the first ElementalType; when sorted, must precede type2
	 * @param type2 the second ElementalType; when sorted, must come after type1
	 */
	private void combineToolMaterials(ElementalTypes type1, ElementalTypes type2) {
		// the materials based on the types
		Item.ToolMaterial firstMaterial = ElementalUtils.getInstance().getToolMaterialFromElementalType(type1);
		Item.ToolMaterial secondMaterial = ElementalUtils.getInstance().getToolMaterialFromElementalType(type2);
		// get the name for the material
		String keyName = type1.getTypeName() + "_" + type2.getTypeName();
		String materialName = "material_tool_" + keyName;
		// always go for the higher of the 2 stats (e.g. if an earth element is paired with an Ice element, the combined has 4k durability and 50 enchantability)
		int harvestLevel = max(firstMaterial.getHarvestLevel(), secondMaterial.getHarvestLevel());
		int durability = max(firstMaterial.getMaxUses(), secondMaterial.getMaxUses());
		float efficiency = max(firstMaterial.getEfficiency(), secondMaterial.getEfficiency());
		float entityDamage = max(firstMaterial.getAttackDamage(), secondMaterial.getAttackDamage());
		int enchantability = max(firstMaterial.getEnchantability(), secondMaterial.getEnchantability());
		// finally create and return the created material
		Item.ToolMaterial combined = addToolMaterial(materialName, harvestLevel, durability, efficiency, entityDamage, enchantability);
		this.dualMaterials.put(keyName, combined);
	}

	private static class SingletonHelper {
		private static final ElementalMaterials instance = new ElementalMaterials();
	}
}
