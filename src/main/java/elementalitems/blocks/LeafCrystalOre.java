package elementalitems.blocks;

import elementalitems.ElementalType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Collections;

public class LeafCrystalOre extends BaseOre {

	public LeafCrystalOre() {
		super(ElementalType.LEAF, Material.ROCK);
		this.blocksThisCanGenerateOver = Collections.singletonList(Blocks.STONE);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA);
	}
}
