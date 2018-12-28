package elementalitems.items;

import elementalitems.ElementalTypes;

/**
 * The interface Elemental item.
 */
public interface ElementalItem {

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * Gets type.
	 *
	 * @return the type
	 */
	ElementalTypes getType();
}
