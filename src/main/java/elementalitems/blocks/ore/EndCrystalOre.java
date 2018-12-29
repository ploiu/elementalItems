package elementalitems.blocks.ore;

import elementalitems.ElementalTypes;
import elementalitems.items.ItemHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

import java.util.Collections;
import java.util.Random;

public class EndCrystalOre extends BaseOre {

	public EndCrystalOre() {
		super(ElementalTypes.ENDER, Material.ROCK);
		this.blocksThisCanGenerateOver = Collections.singletonList(Blocks.END_STONE);
		this.biomesToGenerateIn = Collections.singleton(Biomes.SKY);
		this.minYGeneration = 0;
		this.maxYGeneration = 255;
		this.maxVeinSize = 10;
		this.spawnChances = 30;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return (Item) ItemHandler.enderCrystal;
	}
}


