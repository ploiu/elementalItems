package ploiu.elementalitems.blocks.ores;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.*;
import net.minecraft.state.IProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Arrays;
import java.util.HashMap;

import static net.minecraft.block.Blocks.NETHERRACK;

public class NetherCrystalOre extends FireCrystalOre {
	public NetherCrystalOre() {
		super("ore_fire_nether");
		this.blocksThisCanGenerateOver = Arrays.asList(
				NETHERRACK
		);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(
				BiomeDictionary.Type.NETHER
		);
		this.biomesToGenerateIn.add(Biomes.NETHER);
	}

	@Override
	public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
		super.onExplosionDestroy(worldIn, pos, explosionIn);
		// spawn a block of lava where the block was
		worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState());
	}

	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
		super.onBlockHarvested(worldIn, pos, state, player);
	}

	@Override
	public boolean removedByPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, boolean willHarvest, IFluidState fluid) {
		// don't want to destroy their builds or anything
		if(!player.abilities.isCreativeMode && this.RANDOM.nextInt(10) < 2) {
			return super.removedByPlayer(state, world, pos, player, willHarvest, Fluids.LAVA.getStillFluidState(false));
		} else {
			return super.removedByPlayer(state, world, pos, player, willHarvest, fluid);
		}
	}
}
