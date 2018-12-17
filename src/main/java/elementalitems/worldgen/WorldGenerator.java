package elementalitems.worldgen;

import elementalitems.blocks.BaseOre;
import elementalitems.blocks.FireCrystalOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

import static elementalitems.blocks.BlockHandler.*;

public class WorldGenerator implements IWorldGenerator {
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		// generate based on the dimension we're in
		switch(world.provider.getDimension()) {
			// nether
			case -1:
				this.generateNether(random, chunkX, chunkZ, world);
				break;
			// overworld
			case 0:
				this.generateOverworld(random, world, chunkX, chunkZ);
				break;
			// end
			case 1:
				// TODO generateEnd
				break;
			default:
				break;
		}
	}

	private void runGenerator(@Nonnull BaseOre blockToGen, @Nullable IBlockState state, int amountToGen, int chancesToSpawn, int minHeight, int maxHeight, World world, Random random, int chunkX, int chunkZ) {
		if(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight) {
			throw new IllegalArgumentException("Invalid height arguments for world generator!");
		}
		state = (state == null) ? blockToGen.getDefaultState() : state;
		// the thing that will actually generate our ore
		WorldGenMinable generator = new WorldGenMinable(state, amountToGen, blockToGen.getGeneratorPredicate());
		// get the difference in height
		int heightDiff = maxHeight - minHeight + 1;
		// loop chancesToSpawn times and attempt to generate our blocks in a random position in the chunk
		for(int i = 0; i < chancesToSpawn; i++) {
			// create the x, y, and z locations in the chunk to spawn our ore
			int x = chunkX * 16 + random.nextInt(8);
			int y = minHeight + random.nextInt(heightDiff);
			int z = chunkZ * 16 + random.nextInt(8);

			BlockPos generatePos = new BlockPos(x, y, z);
			// attempt to generate
			if(blockToGen.getBiomesToGenerateIn().contains(world.getBiome(generatePos))) {
				generator.generate(world, random, generatePos);
			}
		}
	}

	private void generateNether(Random random, int chunkX, int chunkZ, World world) {
		this.runGenerator(fireCrystalOre, fireCrystalOre.getBlockState().getBaseState().withProperty(FireCrystalOre.textureProperty, 1), fireCrystalOre.getMaxVeinSize() * 2, 12, 0, 255, world, random, chunkX, chunkZ);
	}

	private void generateOverworld(Random random, World world, int chunkX, int chunkZ) {
		this.runGenerator(fireCrystalOre, null, fireCrystalOre.getMaxVeinSize(), fireCrystalOre.getSpawnChances(), fireCrystalOre.getMinYGeneration(), fireCrystalOre.getMaxYGeneration(), world, random, chunkX, chunkZ);
		this.runGenerator(iceCrystalOre, null, iceCrystalOre.getMaxVeinSize(), iceCrystalOre.getSpawnChances(), iceCrystalOre.getMinYGeneration(), iceCrystalOre.getMaxYGeneration(), world, random, chunkX, chunkZ);
		this.runGenerator(leafCrystalOre, null, leafCrystalOre.getMaxVeinSize(), leafCrystalOre.getSpawnChances(), leafCrystalOre.getMinYGeneration(), leafCrystalOre.getMaxYGeneration(), world, random, chunkX, chunkZ);
		this.runGenerator(waterCrystalOre, null, waterCrystalOre.getMaxVeinSize(), waterCrystalOre.getSpawnChances(), waterCrystalOre.getMinYGeneration(), waterCrystalOre.getMaxYGeneration(), world, random, chunkX, chunkZ);
		this.runGenerator(plainCrystalOre, null, plainCrystalOre.getMaxVeinSize(), plainCrystalOre.getSpawnChances(), plainCrystalOre.getMinYGeneration(), plainCrystalOre.getMaxYGeneration(), world, random, chunkX, chunkZ);
		this.runGenerator(airCrystalOre, null, airCrystalOre.getMaxVeinSize(), airCrystalOre.getSpawnChances(), airCrystalOre.getMinYGeneration(), airCrystalOre.getMaxYGeneration(), world, random, chunkX, chunkZ);
		this.runGenerator(earthCrystalOre, null, earthCrystalOre.getMaxVeinSize(), earthCrystalOre.getSpawnChances(), earthCrystalOre.getMinYGeneration(), earthCrystalOre.getMaxYGeneration(), world, random, chunkX, chunkZ);
	}
}
