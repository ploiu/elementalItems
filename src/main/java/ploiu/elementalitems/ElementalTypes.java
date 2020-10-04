package ploiu.elementalitems;

import java.util.Arrays;

public enum ElementalTypes {
	PLAIN("plain"), FIRE("fire"), ICE("ice"), WATER("water"), LEAF("leaf"), EARTH("earth"), AIR("air"), ENDER("ender");
	private final String typeName;

	ElementalTypes(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * used to enforce a standard naming convention with the elemental types
	 *
	 * @param types the types to sort based on the ordering of how they're declared
	 * @return types, but sorted ;)
	 */
	public static ElementalTypes[] sort(ElementalTypes... types) {
		// sort types and return it
		Arrays.sort(types);
		return types;
	}

	public String getTypeName() {
		return this.typeName;
	}

	@Override
	public String toString() {
		return this.getTypeName();
	}
}