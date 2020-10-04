package ploiu.elementalitems.items;

import net.minecraft.item.ItemGroup;
import ploiu.elementalitems.ElementalTypes;

public class BaseCrystal extends BaseItem {
	public BaseCrystal(ElementalTypes type) {
		super(type, "crystal", new Properties().group(ItemGroup.MISC));
	}
}
