package ploiu.elementalitems.blocks.ores;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ToolType;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.blocks.BaseBlock;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public abstract class BaseOre extends BaseBlock {
	// the list of blocks this can replace during oreGen
	protected List<Block> blocksThisCanGenerateOver;

	// the list of biomes this block can generate in
	protected Set<Biome> biomesToGenerateIn;
	
	protected float minBiomeTemperature;
	protected float maxBiomeTemperature;

	// the max and min y for generation
	protected int minYGeneration = 0;
	protected int maxYGeneration = 64;
	// the max vein size
	protected int maxVeinSize = 5;
	// the max chances per chunk this can spawn
	protected int spawnChances = 3;

	public BaseOre(ElementalTypes type, Properties properties) {
		super(type, properties, String.format("ore_%s", type.getTypeName()));
	}

	public BaseOre(ElementalTypes type) {
		super(type, Properties.create(Material.ROCK)
				            .harvestTool(ToolType.PICKAXE)
				            .harvestLevel(Blocks.DIAMOND_ORE.getDefaultState().getHarvestLevel() + 1)
				            .hardnessAndResistance(5.0f, 6.0f),
				String.format("ore_%s", type.getTypeName()));
	}

	protected BaseOre(ElementalTypes type, Properties properties, String name) {
		super(type, properties, name);
	}

	public Set<Biome> getBiomesToGenerateIn() {
		return this.biomesToGenerateIn;
	}

	public int getMinYGeneration() {
		return this.minYGeneration;
	}

	public int getMaxYGeneration() {
		return this.maxYGeneration;
	}

	public Predicate<BlockState> getGeneratorPredicate() {
		return input -> input != null && this.blocksThisCanGenerateOver.contains(input.getBlock());
	}

	public List<Block> getBlocksToGenerateOver() {
		return this.blocksThisCanGenerateOver;
	}

	public int getMaxVeinSize() {
		return this.maxVeinSize;
	}

	public int getSpawnChances() {
		return this.spawnChances;
	}
}
