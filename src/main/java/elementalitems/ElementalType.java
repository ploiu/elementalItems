package elementalitems;

public enum ElementalType {
	FIRE("fire"), WATER("water"), EARTH("earth"), AIR("air"), ICE("ice"), ENDER("ender"), LEAF("leaf"), PLAIN("plain");
	private final String typeName;

	ElementalType(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeName() {
		return this.typeName;
	}
}
