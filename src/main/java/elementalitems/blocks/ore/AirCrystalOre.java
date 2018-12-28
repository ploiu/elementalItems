package elementalitems.blocks.ore;

import elementalitems.ElementalTypes;
import elementalitems.items.ItemHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Arrays;
import java.util.Random;

public class AirCrystalOre extends BaseOre {

	public AirCrystalOre() {
		super(ElementalTypes.AIR, Material.ROCK);
		this.blocksThisCanGenerateOver = Arrays.asList(Blocks.STONE, Blocks.COBBLESTONE);
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
		// minimum generation height should be high up
		this.minYGeneration = 150;
		this.maxYGeneration = 255;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return (Item) ItemHandler.airCrystal;
	}
}
