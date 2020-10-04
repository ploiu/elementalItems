package ploiu.elementalitems.items;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import ploiu.elementalitems.ElementalTypes;

import java.util.ArrayList;
import java.util.List;

public class ElementalItemsItemRegistry {
	private static List<BaseItem> items = new ArrayList<>();

	public static BaseCrystal plainCrystal = new BaseCrystal(ElementalTypes.PLAIN);
	public static BaseCrystal fireCrystal = new BaseCrystal(ElementalTypes.FIRE);
	public static BaseCrystal waterCrystal = new BaseCrystal(ElementalTypes.WATER);
	public static BaseCrystal leafCrystal = new BaseCrystal(ElementalTypes.LEAF);
	public static BaseCrystal earthCrystal = new BaseCrystal(ElementalTypes.EARTH);
	public static BaseCrystal airCrystal = new BaseCrystal(ElementalTypes.AIR);
	public static BaseCrystal enderCrystal = new BaseCrystal(ElementalTypes.ENDER);

	private ElementalItemsItemRegistry() {
	}

	public static void addItem(BaseItem item) {
		items.add(item);
	}
	
	public static void registerItems(RegistryEvent.Register<Item> event) {
		// register all the items
		items.forEach(event.getRegistry()::register);
	}
	
}
