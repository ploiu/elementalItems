package elementalitems.items;

import elementalitems.ElementalTypes;
import net.minecraft.item.ItemStack;

import java.util.Objects;

/**
 * The base class for our crystals
 */
public class BaseCrystal extends BaseItem implements ElementalItem {

	/**
	 * Instantiates a new Base crystal.
	 *
	 * @param type the type
	 */
	public BaseCrystal(ElementalTypes type) {
		super("crystal_" + type.getTypeName(), type);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isBeaconPayment(ItemStack stack) {
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.type);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof BaseCrystal && ((BaseCrystal) other).getName().equals(this.name);
	}

	@Override
	public String toString() {
		return "BaseCrystal{" +
				       "name='" + this.name + '\'' +
				       ", type=" + this.type +
				       '}';
	}
}
