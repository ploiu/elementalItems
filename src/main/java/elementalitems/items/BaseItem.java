package elementalitems.items;

import elementalitems.ElementalTypes;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * The type Base item.
 */
public class BaseItem extends Item implements ElementalItem {
	/**
	 * Instantiates a new Base item.
	 *
	 * @param name the name
	 * @param type the type
	 */
	public BaseItem(String name, ElementalTypes type) {
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.type = type;
		//add our item to the registration arraylist for easy registration
		ItemHandler.items.add(this);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ElementalTypes getType() {
		return this.type;
	}

	/**
	 * The Name.
	 */
	protected final String name;
	/**
	 * The Type.
	 */
	protected final ElementalTypes type;
}
