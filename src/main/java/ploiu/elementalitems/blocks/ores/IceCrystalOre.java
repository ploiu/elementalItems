package ploiu.elementalitems.blocks.ores;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import ploiu.elementalitems.ElementalTypes;

import java.util.Arrays;
import java.util.HashSet;

public class IceCrystalOre extends BaseOre {
	public IceCrystalOre() {
		super(ElementalTypes.ICE, Properties.of(Material.ICE)
				                          .strength(0.5f, 3.0f)
				                          .friction(0.98f)
				                          .sound(SoundType.GLASS)
				                          .harvestLevel(0)
				                          .noOcclusion()
		);
		this.blocksThisCanGenerateOver = Arrays.asList(
				Blocks.ICE,
				Blocks.PACKED_ICE
		);
		this.biomesToGenerateIn = new HashSet<>();/* this.getBiomesToGenerateInFromTypes(
				BiomeDictionary.Type.COLD,
				BiomeDictionary.Type.SNOWY,
				BiomeDictionary.Type.MOUNTAIN
		);*/
		this.maxVeinSize = 10;
		this.spawnChances = 100;
		this.maxYGeneration = 255;
	}
}
