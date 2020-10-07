package ploiu.elementalitems.items.unique;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolItem;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItem;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.items.tools.itemtiers.TierRegistry;

import java.util.HashSet;

public class ArmorCrusher extends ToolItem implements ElementalItem {


	public ArmorCrusher() {
		super(10, -3.3f, TierRegistry.earthTier, new HashSet<>(), new Properties().group(ItemGroup.COMBAT).maxDamage(4000));
		this.setRegistryName("warhammer");
		ElementalItemsItemRegistry.addItem(this);
	}

	@Override
	public ElementalTypes getType() {
		return ElementalTypes.EARTH;
	}
}
