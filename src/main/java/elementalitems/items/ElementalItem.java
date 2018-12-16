package elementalitems.items;

import elementalitems.ElementalType;

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
	ElementalType getType();
}
