package ploiu.elementalitems.blocks.ores;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ToolType;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.blocks.BaseBlock;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class BaseOre extends BaseBlock {
	// the list of blocks this can replace during oreGen
	protected List<Block> blocksThisCanGenerateOver;

	// the list of biomes this block can generate in
	protected Set<Biome> biomesToGenerateIn;

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
				            .harvestLevel(3)
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

	/**
	 * takes a variable amount of {@link BiomeDictionary.Type Biome Types} and returns a Set of {@link Biome Biomes}
	 * that are registered with any of those types
	 *
	 * @param types the list of Types that describe the biomes we want to get from the {@link BiomeDictionary}
	 * @return a Set of Biomes from the passed in types
	 */
	protected Set<Biome> getBiomesToGenerateInFromTypes(BiomeDictionary.Type... types) {
		Set<Biome> biomes = new HashSet<>();
		// get all the biomes from the type and add them
		for(BiomeDictionary.Type type : types) {
			biomes.addAll(BiomeDictionary.getBiomes(type));
		}
		return biomes;
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
