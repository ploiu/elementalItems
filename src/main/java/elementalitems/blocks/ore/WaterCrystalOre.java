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


public class WaterCrystalOre extends BaseOre {

	public WaterCrystalOre() {
		super(ElementalType.WATER, Material.ROCK);
		this.blocksThisCanGenerateOver = Arrays.asList(Blocks.STONE, Blocks.SAND, Blocks.GRAVEL, Blocks.COBBLESTONE);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(BiomeDictionary.Type.WATER, BiomeDictionary.Type.WET);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return (Item) ItemHandler.waterCrystal;
	}
}
