package ploiu.elementalitems.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import ploiu.elementalitems.items.combat.armor.*;
import ploiu.elementalitems.items.combat.weapons.arrows.ElementalArrow;
import ploiu.elementalitems.items.combat.weapons.swords.*;
import ploiu.elementalitems.items.combat.weapons.swords.dual.DualSword;
import ploiu.elementalitems.items.combat.weapons.swords.dual.DualSwordBuilder;
import ploiu.elementalitems.items.crystal.BaseCrystal;
import ploiu.elementalitems.items.tools.axe.*;
import ploiu.elementalitems.items.tools.pickaxe.*;
import ploiu.elementalitems.items.tools.shovel.*;
import ploiu.elementalitems.items.unique.ArmorCrusher;
import ploiu.elementalitems.items.unique.Flamethrower;

import java.util.ArrayList;
import java.util.List;

import static ploiu.elementalitems.ElementalTypes.*;

@SuppressWarnings("unused")
public class ElementalItemsItemRegistry {
	// needs to be public or else intelliJ will move it down below the other items, causing it to be null when items get added to it
	public static final List<Item> items = new ArrayList<>();
	// crystals
	public static BaseCrystal plainCrystal = new BaseCrystal(PLAIN);
	public static BaseCrystal fireCrystal = new BaseCrystal(FIRE);
	public static BaseCrystal iceCrystal = new BaseCrystal(ICE);
	public static BaseCrystal waterCrystal = new BaseCrystal(WATER);
	public static BaseCrystal leafCrystal = new BaseCrystal(LEAF);
	public static BaseCrystal earthCrystal = new BaseCrystal(EARTH);
	public static BaseCrystal airCrystal = new BaseCrystal(AIR);
	public static BaseCrystal enderCrystal = new BaseCrystal(ENDER);
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
	public static DualSword meteorSword = new DualSwordBuilder(FIRE, EARTH).build(); // fire and earth
	public static DualSword coldFireSword = new DualSwordBuilder(FIRE, ICE).build(); // fire and ice
	public static DualSword steamSword = new DualSwordBuilder(FIRE, WATER).build(); // fire and water
	public static DualSword typhoonSword = new DualSwordBuilder(WATER, AIR).build(); // water and air
	public static DualSword blizzardSword = new DualSwordBuilder(ICE, AIR).build(); // ice and air
	public static DualSword voidSword = new DualSwordBuilder(ICE, ENDER).build(); // ice and ender
	public static DualSword jungleSword = new DualSwordBuilder(WATER, LEAF).build(); // water and leaf
	public static DualSword glacialSword = new DualSwordBuilder(WATER, ICE).build(); // water and ice
	public static DualSword pollenSword = new DualSwordBuilder(LEAF, AIR).build(); // leaf and air
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
	public static ElementalArrow plainArrow = new ElementalArrow(PLAIN);
	public static ElementalArrow fireArrow = new ElementalArrow(FIRE);
	public static ElementalArrow iceArrow = new ElementalArrow(ICE);
	public static ElementalArrow waterArrow = new ElementalArrow(WATER);
	public static ElementalArrow leafArrow = new ElementalArrow(LEAF);
	public static ElementalArrow earthArrow = new ElementalArrow(EARTH);
	public static ElementalArrow airArrow = new ElementalArrow(AIR);
	public static ElementalArrow enderArrow = new ElementalArrow(ENDER);
	// unique items TODO
	public static BaseSword swordOfLifeAndDeath = new SwordOfLifeAndDeath();
	public static Flamethrower flamethrower = new Flamethrower();
	public static ArmorCrusher armorCrusher = new ArmorCrusher();

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
