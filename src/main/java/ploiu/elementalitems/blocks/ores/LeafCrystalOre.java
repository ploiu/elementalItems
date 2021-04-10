package ploiu.elementalitems.blocks.ores;

import net.minecraft.block.Blocks;
import ploiu.elementalitems.ElementalTypes;

import java.util.Collections;
import java.util.HashSet;

public class LeafCrystalOre extends BaseOre {
	public LeafCrystalOre() {
		super(ElementalTypes.LEAF);
		this.blocksThisCanGenerateOver = Collections.singletonList(Blocks.STONE);
		this.biomesToGenerateIn = new HashSet<>();/*this.getBiomesToGenerateInFromTypes(
				BiomeDictionary.Type.FOREST,
				BiomeDictionary.Type.JUNGLE,
				BiomeDictionary.Type.LUSH,
				BiomeDictionary.Type.DENSE,
				BiomeDictionary.Type.SWAMP,
				BiomeDictionary.Type.CONIFEROUS,
				BiomeDictionary.Type.SAVANNA
		);*/
	}
}
