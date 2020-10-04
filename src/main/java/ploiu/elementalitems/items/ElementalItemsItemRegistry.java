package ploiu.elementalitems.items;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import ploiu.elementalitems.ElementalTypes;
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

	// arrows


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
