package ploiu.elementalitems.blocks;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import ploiu.elementalitems.ElementalTypes;

import java.util.List;

public abstract class BaseOre extends BaseBlock {
	public BaseOre(ElementalTypes type, Properties properties) {
		super(type, properties, String.format("ore_%s", type.getTypeName()));
	}

	/**
	 * returns a list of blocks that this block can replace during world generation
	 */
	public abstract List<Block> canReplaceDuringWorldGen();

	/**
	 * returns a list of biomes that this block can generate in
	 */
	public abstract List<Biome> biomesCanGenerateIn();
}
