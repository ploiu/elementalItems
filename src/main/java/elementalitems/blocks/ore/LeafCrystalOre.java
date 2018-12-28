package elementalitems.blocks.ore;

import elementalitems.ElementalTypes;
import elementalitems.items.ItemHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Collections;
import java.util.Random;

public class LeafCrystalOre extends BaseOre {

	public LeafCrystalOre() {
		super(ElementalTypes.LEAF, Material.ROCK);
		this.blocksThisCanGenerateOver = Collections.singletonList(Blocks.STONE);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(BiomeDictionary.Type.FOREST, BiomeDictionary.Type.JUNGLE, BiomeDictionary.Type.LUSH, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.SAVANNA);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return (Item) ItemHandler.leafCrystal;
	}
}
