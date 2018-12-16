package elementalitems.items;

import elementalitems.ElementalItems;
import net.minecraft.enchantment.EnchantmentKnockback;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.SoundEvent;

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
	 * The Tool fire.
	 */
	// tool materials
	public Item.ToolMaterial TOOL_FIRE;
	/**
	 * The Tool ice.
	 */
	public Item.ToolMaterial TOOL_ICE;
	/**
	 * The Tool water.
	 */
	public Item.ToolMaterial TOOL_WATER;
	/**
	 * The Tool leaf.
	 */
	public Item.ToolMaterial TOOL_LEAF;
	/**
	 * The Tool earth.
	 */
	public Item.ToolMaterial TOOL_EARTH;
	/**
	 * The Tool air.
	 */
	public Item.ToolMaterial TOOL_AIR;
	/**
	 * The Tool ender.
	 */
	public Item.ToolMaterial TOOL_ENDER;
	/**
	 * The Tool plain.
	 */
	public Item.ToolMaterial TOOL_PLAIN;

	/**
	 * The Armor fire.
	 */
	// armor materials
	public ItemArmor.ArmorMaterial ARMOR_FIRE;
	/**
	 * The Armor ice.
	 */
	public ItemArmor.ArmorMaterial ARMOR_ICE;
	/**
	 * The Armor water.
	 */
	public ItemArmor.ArmorMaterial ARMOR_WATER;
	/**
	 * The Armor leaf.
	 */
	public ItemArmor.ArmorMaterial ARMOR_LEAF;
	/**
	 * The Armor earth.
	 */
	public ItemArmor.ArmorMaterial ARMOR_EARTH;
	/**
	 * The Armor air.
	 */
	public ItemArmor.ArmorMaterial ARMOR_AIR;
	/**
	 * The Armor ender.
	 */
	public ItemArmor.ArmorMaterial ARMOR_ENDER;
	/**
	 * The Armor plain.
	 */
	public ItemArmor.ArmorMaterial ARMOR_PLAIN;

	/**
	 * called in {@link ElementalItems} during forge's init events to initialize and register our materials
	 */
	public void registerMaterials() {
		int BASE_DURABILITY = 2000;
		SoundEvent EQUIP_SOUND_EVENT = SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
		int BASE_ENCHANTABILITY = DIAMOND.getEnchantability();
		int BASE_HARVEST_LEVEL = DIAMOND.getHarvestLevel();
		float BASE_ENTITY_DAMAGE = DIAMOND.getAttackDamage();
		float BASE_EFFICIENCY = DIAMOND.getEfficiency();
		int[] BASE_REDUCTION_AMOUNTS = new int[]{3, 6, 8, 3};
		int ICE_ENCHANTABILITY = 50;
		int EARTH_DURABILITY = 4000;
		int BASE_ARMOR_DURABILITY = 33;
		float BASE_TOUGHNESS = 2.0f;
		int EARTH_ARMOR_DURABILITY = 100;

		this.TOOL_FIRE = addToolMaterial("material_tool_fire", BASE_HARVEST_LEVEL, BASE_DURABILITY, BASE_EFFICIENCY, BASE_ENTITY_DAMAGE, IRON.getEnchantability());
		this.TOOL_ICE = addToolMaterial("material_tool_ice", BASE_HARVEST_LEVEL, 1000, BASE_EFFICIENCY, BASE_ENTITY_DAMAGE, ICE_ENCHANTABILITY);
		this.TOOL_WATER = addToolMaterial("material_tool_water", BASE_HARVEST_LEVEL, BASE_DURABILITY, 50.0f, BASE_ENTITY_DAMAGE, BASE_ENCHANTABILITY);
		this.TOOL_LEAF = addToolMaterial("material_tool_leaf", BASE_HARVEST_LEVEL, BASE_DURABILITY, BASE_EFFICIENCY, BASE_ENTITY_DAMAGE, WOOD.getEnchantability());
		this.TOOL_EARTH = addToolMaterial("material_tool_earth", BASE_HARVEST_LEVEL, EARTH_DURABILITY, BASE_EFFICIENCY, BASE_ENTITY_DAMAGE, BASE_ENCHANTABILITY);
		this.TOOL_AIR = addToolMaterial("material_tool_air", BASE_HARVEST_LEVEL, BASE_DURABILITY, BASE_EFFICIENCY * 1.25f, BASE_ENTITY_DAMAGE, BASE_ENCHANTABILITY);
		this.TOOL_ENDER = addToolMaterial("material_tool_end", BASE_HARVEST_LEVEL, BASE_DURABILITY, BASE_EFFICIENCY, BASE_ENTITY_DAMAGE, BASE_ENCHANTABILITY);
		this.TOOL_PLAIN = addToolMaterial("material_tool_plain", BASE_HARVEST_LEVEL, BASE_DURABILITY, BASE_EFFICIENCY, BASE_ENTITY_DAMAGE, BASE_ENCHANTABILITY);
		// armor
		this.ARMOR_FIRE = addArmorMaterial("material_armor_fire", "elementalitems:armor_fire", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, BASE_ENCHANTABILITY, EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_ICE = addArmorMaterial("material_armor_ice", "elementalitems:armor_ice", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, ICE_ENCHANTABILITY, EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_WATER = addArmorMaterial("material_armor_water", "elementalitems:armor_water", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, BASE_ENCHANTABILITY, EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_LEAF = addArmorMaterial("material_armor_leaf", "elementalitems:armor_leaf", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, WOOD.getEnchantability(), EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_EARTH = addArmorMaterial("material_armor_earth", "elementalitems:armor_earth", EARTH_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, BASE_ENCHANTABILITY, EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_AIR = addArmorMaterial("material_armor_air", "elementalitems:armor_air", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, BASE_ENCHANTABILITY, EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_ENDER = addArmorMaterial("material_armor_ender", "elementalitems:armor_ender", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, BASE_ENCHANTABILITY, EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
		this.ARMOR_PLAIN = addArmorMaterial("material_armor_plain", "elementalitems:armor_plain", BASE_ARMOR_DURABILITY, BASE_REDUCTION_AMOUNTS, BASE_ENCHANTABILITY, EQUIP_SOUND_EVENT, BASE_TOUGHNESS);
	}


	private static class SingletonHelper {
		private static final ElementalMaterials instance = new ElementalMaterials();
	}
}
