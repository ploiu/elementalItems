package ploiu.elementalitems.blocks.ores;

import net.minecraft.block.Blocks;
import ploiu.elementalitems.ElementalTypes;

import java.util.Arrays;

public class AirCrystalOre extends BaseOre {
	public AirCrystalOre() {
		super(ElementalTypes.AIR);

		this.blocksThisCanGenerateOver = Arrays.asList(Blocks.STONE, Blocks.COBBLESTONE);
		this.minBiomeTemperature = -10f;
		this.maxBiomeTemperature = 10f;
		// minimum generation height should be high up
		this.minYGeneration = 120;
		this.maxYGeneration = 255;
	}
}
