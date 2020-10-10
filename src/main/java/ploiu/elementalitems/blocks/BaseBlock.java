package ploiu.elementalitems.blocks;

import net.minecraft.block.BreakableBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import ploiu.elementalitems.ElementalTypes;

public abstract class BaseBlock extends BreakableBlock {
	protected final ElementalTypes type;

	protected BaseBlock(ElementalTypes type, Properties properties, String name) {
		super(properties);
		this.type = type;
		this.setRegistryName(name);
		ElementalItemsBlockRegistry.addBlock(this);
	}

	public BaseBlock(ElementalTypes type, Properties properties) {
		this(type, properties, String.format("block_%s", type.getTypeName()));
	}

	public BaseBlock(ElementalTypes type) {
		this(type, Properties.create(Material.ROCK)
				           .hardnessAndResistance(5.0f, 6.0f)
				           .harvestLevel(3)
				           .harvestTool(ToolType.PICKAXE),
				String.format("block_%s", type.getTypeName())
		);
	}

	public ElementalTypes getType() {
		return this.type;
	}
}
