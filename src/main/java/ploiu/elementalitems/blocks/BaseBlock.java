package ploiu.elementalitems.blocks;

import net.minecraft.block.BreakableBlock;
import ploiu.elementalitems.ElementalTypes;

public abstract class BaseBlock extends BreakableBlock {
	protected final ElementalTypes type;

	protected BaseBlock(ElementalTypes type, Properties properties, String name) {
		super(properties);
		this.type = type;
		this.setRegistryName(name);
		ElementalItemsBlockRegistry.addBlock(this);
	}

	public ElementalTypes getType() {
		return this.type;
	}
}
