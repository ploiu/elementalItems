package elementalitems.blocks.ore;

import elementalitems.ElementalType;
import elementalitems.items.ItemHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Arrays;
import java.util.Random;

public class PlainCrystalOre extends BaseOre {
	public PlainCrystalOre() {
		super(ElementalType.PLAIN, Material.ROCK);
		this.blocksThisCanGenerateOver = Arrays.asList(Blocks.STONE, Blocks.COBBLESTONE, Blocks.GRAVEL);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(
				BiomeDictionary.Type.WET,
				BiomeDictionary.Type.DRY,
				BiomeDictionary.Type.HOT,
				BiomeDictionary.Type.COLD,
				BiomeDictionary.Type.PLAINS,
				BiomeDictionary.Type.OCEAN,
				BiomeDictionary.Type.MOUNTAIN,
				BiomeDictionary.Type.FOREST,
				BiomeDictionary.Type.RIVER,
				BiomeDictionary.Type.SNOWY,
				BiomeDictionary.Type.RARE,
				BiomeDictionary.Type.BEACH,
				BiomeDictionary.Type.SANDY,
				BiomeDictionary.Type.SAVANNA
		);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return (Item) ItemHandler.plainCrystal;
	}
}
