package elementalitems.items;

import elementalitems.ElementalType;
import net.minecraft.item.ItemStack;

/**
 * The base class for our crystals
 */
public class BaseCrystal extends BaseItem implements ElementalItem {

	/**
	 * Instantiates a new Base crystal.
	 *
	 * @param type the type
	 */
	public BaseCrystal(ElementalType type) {
		super("crystal_" + type.getTypeName(), type);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public ElementalType getType() {
		return super.getType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isBeaconPayment(ItemStack stack) {
		return true;
	}

}
