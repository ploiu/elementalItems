package ploiu.elementalitems.items;

import net.minecraft.item.Item;
import ploiu.elementalitems.ElementalTypes;

public class BaseItem extends Item implements ElementalItem {
	private final ElementalTypes type;
	private final String itemName;

	public BaseItem(ElementalTypes type, String name) {
		this(type, name, new Properties());
	}

	public BaseItem(ElementalTypes type, String name, Properties properties) {
		super(properties);
		this.type = type;
		// itemType_elementType
		this.itemName = String.format("%s_%s", name, this.type.getTypeName());
		this.setRegistryName(this.itemName);
		ElementalItemsItemRegistry.addItem(this);
	}

	@Override
	public ElementalTypes getType() {
		return this.type;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		} else if(obj == this) {
			return true;
		} else if(obj instanceof BaseItem) {
			return (((BaseItem) obj).itemName).equalsIgnoreCase(this.itemName);
		} else {
			return false;
		}
	}
}
