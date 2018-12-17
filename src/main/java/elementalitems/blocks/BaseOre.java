package elementalitems.blocks;

import com.google.common.base.Predicate;
import elementalitems.ElementalType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The base class for any of our ore blocks. Contains fields that can be used by inheritors of this class for
 * controlling the biomes, blocks, and locations the ore can spawn in
 */
public abstract class BaseOre extends BaseBlock {

	// the type associated with this 
	protected ElementalType type;

	// the list of blocks this can replace during oreGen
	protected List<Block> blocksThisCanGenerateOver;

	// the list of biomes this block can generate in
	protected Set<Biome> biomesToGenerateIn;

	// the max and min y for generation
	protected int minYGeneration = 0;
	protected int maxYGeneration = 63;
	// the max vein size
	protected int maxVeinSize = 5;
	// the max chances per chunk this can spawn
	protected int spawnChances = 1;


	public BaseOre(ElementalType oreType, Material blockMaterial) {
		super("ore_" + oreType.getTypeName(), blockMaterial);
		this.type = oreType;
		this.blocksThisCanGenerateOver = Collections.singletonList(Blocks.STONE);
		this.biomesToGenerateIn = BiomeDictionary.getBiomes(BiomeDictionary.Type.MAGICAL);
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

	public Predicate<IBlockState> getGeneratorPredicate() {
		return input -> input != null && this.blocksThisCanGenerateOver.contains(input.getBlock());
	}


	public int getMaxVeinSize() {
		return this.maxVeinSize;
	}

	public int getSpawnChances() {
		return this.spawnChances;
	}
}
