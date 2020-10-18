package ploiu.elementalitems.blocks.ores;

import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ToolType;
import ploiu.elementalitems.ElementalTypes;

import java.util.Arrays;

public class IceCrystalOre extends BaseOre {
	public IceCrystalOre() {
		super(ElementalTypes.ICE, Properties.create(Material.ICE)
				                          .hardnessAndResistance(0.5f, 3.0f)
				                          .slipperiness(0.98f)
				                          .sound(SoundType.GLASS)
				                          .harvestLevel(0)
		);
		this.blocksThisCanGenerateOver = Arrays.asList(
				Blocks.ICE,
				Blocks.PACKED_ICE
		);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(
				BiomeDictionary.Type.COLD,
				BiomeDictionary.Type.SNOWY,
				BiomeDictionary.Type.MOUNTAIN
		);
		this.maxVeinSize = 10;
		this.spawnChances = 100;
		this.maxYGeneration = 255;
	}

	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
}
