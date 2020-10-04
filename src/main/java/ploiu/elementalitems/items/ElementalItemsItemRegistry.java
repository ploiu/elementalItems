package ploiu.elementalitems.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.combat.armor.*;
import ploiu.elementalitems.items.combat.weapons.arrows.ElementalArrow;
import ploiu.elementalitems.items.combat.weapons.swords.*;
import ploiu.elementalitems.items.tools.axe.*;
import ploiu.elementalitems.items.tools.pickaxe.*;
import ploiu.elementalitems.items.tools.shovel.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ElementalItemsItemRegistry {
	// needs to be public or else intelliJ will move it down below the other items, causing it to be null
	public static final List<Item> items = new ArrayList<>();
	// crystals
	public static BaseCrystal plainCrystal = new BaseCrystal(ElementalTypes.PLAIN);
	public static BaseCrystal fireCrystal = new BaseCrystal(ElementalTypes.FIRE);
	public static BaseCrystal iceCrystal = new BaseCrystal(ElementalTypes.ICE);
	public static BaseCrystal waterCrystal = new BaseCrystal(ElementalTypes.WATER);
	public static BaseCrystal leafCrystal = new BaseCrystal(ElementalTypes.LEAF);
	public static BaseCrystal earthCrystal = new BaseCrystal(ElementalTypes.EARTH);
	public static BaseCrystal airCrystal = new BaseCrystal(ElementalTypes.AIR);
	public static BaseCrystal enderCrystal = new BaseCrystal(ElementalTypes.ENDER);
	// axes
	public static BaseAxe plainAxe = new PlainAxe();
	public static BaseAxe fireAxe = new FireAxe();
	public static BaseAxe iceAxe = new IceAxe();
	public static BaseAxe waterAxe = new WaterAxe();
	public static BaseAxe leafAxe = new LeafAxe();
	public static BaseAxe earthAxe = new EarthAxe();
	public static BaseAxe airAxe = new AirAxe();
	public static BaseAxe enderAxe = new EnderAxe();
	// pickaxes
	public static BasePickaxe plainPickaxe = new PlainPickaxe();
	public static BasePickaxe firePickaxe = new FirePickaxe();
	public static BasePickaxe icePickaxe = new IcePickaxe();
	public static BasePickaxe waterPickaxe = new WaterPickaxe();
	public static BasePickaxe leafPickaxe = new LeafPickaxe();
	public static BasePickaxe earthPickaxe = new EarthPickaxe();
	public static BasePickaxe airPickaxe = new AirPickaxe();
	public static BasePickaxe enderPickaxe = new EnderPickaxe();
	// shovels
	public static BaseShovel plainShovel = new PlainShovel();
	public static BaseShovel fireShovel = new FireShovel();
	public static BaseShovel iceShovel = new IceShovel();
	public static BaseShovel waterShovel = new WaterShovel();
	public static BaseShovel leafShovel = new LeafShovel();
	public static BaseShovel earthShovel = new EarthShovel();
	public static BaseShovel airShovel = new AirShovel();
	public static BaseShovel enderShovel = new EnderShovel();
	// swords
	public static BaseSword plainSword = new PlainSword();
	public static BaseSword fireSword = new FireSword();
	public static BaseSword iceSword = new IceSword();
	public static BaseSword waterSword = new WaterSword();
	public static BaseSword leafSword = new LeafSword();
	public static BaseSword earthSword = new EarthSword();
	public static BaseSword airSword = new AirSword();
	public static BaseSword enderSword = new EnderSword();
	// dual swords

	// armor
	public static BaseArmorItem plainHelmet = new PlainArmor(EquipmentSlotType.HEAD);
	public static BaseArmorItem plainChestplate = new PlainArmor(EquipmentSlotType.CHEST);
	public static BaseArmorItem plainLeggings = new PlainArmor(EquipmentSlotType.LEGS);
	public static BaseArmorItem plainBoots = new PlainArmor(EquipmentSlotType.FEET);

	public static BaseArmorItem fireHelmet = new FireArmor(EquipmentSlotType.HEAD);
	public static BaseArmorItem fireChestplate = new FireArmor(EquipmentSlotType.CHEST);
	public static BaseArmorItem fireLeggings = new FireArmor(EquipmentSlotType.LEGS);
	public static BaseArmorItem fireBoots = new FireArmor(EquipmentSlotType.FEET);

	public static BaseArmorItem iceHelmet = new IceArmor(EquipmentSlotType.HEAD);
	public static BaseArmorItem iceChestplate = new IceArmor(EquipmentSlotType.CHEST);
	public static BaseArmorItem iceLeggings = new IceArmor(EquipmentSlotType.LEGS);
	public static BaseArmorItem iceBoots = new IceArmor(EquipmentSlotType.FEET);

	public static BaseArmorItem waterHelmet = new WaterArmor(EquipmentSlotType.HEAD);
	public static BaseArmorItem waterChestplate = new WaterArmor(EquipmentSlotType.CHEST);
	public static BaseArmorItem waterLeggings = new WaterArmor(EquipmentSlotType.LEGS);
	public static BaseArmorItem waterBoots = new WaterArmor(EquipmentSlotType.FEET);

	public static BaseArmorItem leafHelmet = new LeafArmor(EquipmentSlotType.HEAD);
	public static BaseArmorItem leafChestplate = new LeafArmor(EquipmentSlotType.CHEST);
	public static BaseArmorItem leafLeggings = new LeafArmor(EquipmentSlotType.LEGS);
	public static BaseArmorItem leafBoots = new LeafArmor(EquipmentSlotType.FEET);

	public static BaseArmorItem earthHelmet = new EarthArmor(EquipmentSlotType.HEAD);
	public static BaseArmorItem earthChestplate = new EarthArmor(EquipmentSlotType.CHEST);
	public static BaseArmorItem earthLeggings = new EarthArmor(EquipmentSlotType.LEGS);
	public static BaseArmorItem earthBoots = new EarthArmor(EquipmentSlotType.FEET);

	public static BaseArmorItem airHelmet = new AirArmor(EquipmentSlotType.HEAD);
	public static BaseArmorItem airChestplate = new AirArmor(EquipmentSlotType.CHEST);
	public static BaseArmorItem airLeggings = new AirArmor(EquipmentSlotType.LEGS);
	public static BaseArmorItem airBoots = new AirArmor(EquipmentSlotType.FEET);

	public static BaseArmorItem enderHelmet = new EnderArmor(EquipmentSlotType.HEAD);
	public static BaseArmorItem enderChestplate = new EnderArmor(EquipmentSlotType.CHEST);
	public static BaseArmorItem enderLeggings = new EnderArmor(EquipmentSlotType.LEGS);
	public static BaseArmorItem enderBoots = new EnderArmor(EquipmentSlotType.FEET);

	// arrows
	public static ElementalArrow plainArrow = new ElementalArrow(ElementalTypes.PLAIN);
	public static ElementalArrow fireArrow = new ElementalArrow(ElementalTypes.FIRE);
	public static ElementalArrow iceArrow = new ElementalArrow(ElementalTypes.ICE);
	public static ElementalArrow waterArrow = new ElementalArrow(ElementalTypes.WATER);
	public static ElementalArrow leafArrow = new ElementalArrow(ElementalTypes.LEAF);
	public static ElementalArrow earthArrow = new ElementalArrow(ElementalTypes.EARTH);
	public static ElementalArrow airArrow = new ElementalArrow(ElementalTypes.AIR);
	public static ElementalArrow enderArrow = new ElementalArrow(ElementalTypes.ENDER);


	private ElementalItemsItemRegistry() {
	}

	public static void addItem(Item item) {
		items.add(item);
	}

	public static void registerItems(RegistryEvent.Register<Item> event) {
		// register all the items
		items.forEach(event.getRegistry()::register);
	}
}
