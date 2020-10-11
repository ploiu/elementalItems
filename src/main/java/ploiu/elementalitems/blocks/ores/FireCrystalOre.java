package ploiu.elementalitems.blocks.ores;

import net.minecraft.block.material.Material;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ToolType;
import ploiu.elementalitems.ElementalTypes;

import java.util.Arrays;

import static net.minecraft.block.Blocks.*;

public class FireCrystalOre extends BaseOre {
	public FireCrystalOre() {
		super(ElementalTypes.FIRE, Properties.create(Material.ROCK)
				                           .harvestTool(ToolType.PICKAXE)
				                           .harvestLevel(2)
				                           .hardnessAndResistance(5.0f, 6.0f)
				                           .lightValue(15));
		this.blocksThisCanGenerateOver = Arrays.asList(
				STONE,
				COBBLESTONE,
				LAVA,
				NETHERRACK
		);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(
				BiomeDictionary.Type.HOT,
				BiomeDictionary.Type.MOUNTAIN,
				BiomeDictionary.Type.NETHER,
				BiomeDictionary.Type.DRY,
				BiomeDictionary.Type.HILLS
		);
		this.biomesToGenerateIn.add(Biomes.NETHER);
	}
	
	protected FireCrystalOre(String registryName) {
		super(ElementalTypes.FIRE, Properties.create(Material.ROCK)
				                           .harvestTool(ToolType.PICKAXE)
				                           .harvestLevel(2)
				                           .hardnessAndResistance(5.0f, 6.0f)
				                           .lightValue(15), registryName);
	}
}
