package elementalitems.blocks;

import elementalitems.ElementalType;
import elementalitems.items.ItemHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Arrays;
import java.util.Random;

public class EarthCrystalOre extends BaseOre {

	public EarthCrystalOre() {
		super(ElementalType.EARTH, Material.ROCK);
		// override any type of hardened clay as well as stone
		this.blocksThisCanGenerateOver = Arrays.asList(Blocks.STONE, Blocks.COBBLESTONE, Blocks.HARDENED_CLAY, Blocks.STAINED_HARDENED_CLAY);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(
				BiomeDictionary.Type.SANDY,
				BiomeDictionary.Type.MESA,
				BiomeDictionary.Type.MOUNTAIN
		);
		this.maxYGeneration = 100; // not too high up, but high enough to replace clay and stuff in mesas
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return (Item) ItemHandler.earthCrystal;
	}
}
