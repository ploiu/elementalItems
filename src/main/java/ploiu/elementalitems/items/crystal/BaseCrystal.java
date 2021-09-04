package ploiu.elementalitems.items.crystal;

import net.minecraft.item.ItemGroup;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.BaseItem;

public class BaseCrystal extends BaseItem {
	public BaseCrystal(ElementalTypes type) {
		super(type, "crystal", new Properties().tab(ItemGroup.TAB_MISC));
	}
}
