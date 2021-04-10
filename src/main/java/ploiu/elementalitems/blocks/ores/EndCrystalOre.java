package ploiu.elementalitems.blocks.ores;

import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biomes;
import ploiu.elementalitems.ElementalTypes;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class EndCrystalOre extends BaseOre {
	public EndCrystalOre() {
		super(ElementalTypes.ENDER);
		this.blocksThisCanGenerateOver = Collections.singletonList(Blocks.END_STONE);
		this.biomesToGenerateIn = new HashSet<>();
		/*this.biomesToGenerateIn.add(Biomes.END_BARRENS);
		this.biomesToGenerateIn.add(Biomes.END_HIGHLANDS);
		this.biomesToGenerateIn.add(Biomes.END_MIDLANDS);
		this.biomesToGenerateIn.add(Biomes.SMALL_END_ISLANDS);
		this.biomesToGenerateIn.add(Biomes.THE_END);*/
		this.minYGeneration = 0;
		this.maxYGeneration = 255;
		this.maxVeinSize = 10;
		this.spawnChances = 30;
	}
}
