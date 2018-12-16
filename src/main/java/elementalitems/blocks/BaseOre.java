package elementalitems.blocks;

import elementalitems.ElementalType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseOre extends BaseBlock {

	// the type associated with this 
	protected ElementalType type;


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

	protected Set<Biome> getBiomesToGenerateInFromTypes(BiomeDictionary.Type... types) {
		Set<Biome> biomes = new HashSet<>();
		// get all the biomes from the type and add them
		for(BiomeDictionary.Type type : types) {
			biomes.addAll(BiomeDictionary.getBiomes(type));
		}
		return biomes;
	}
}
