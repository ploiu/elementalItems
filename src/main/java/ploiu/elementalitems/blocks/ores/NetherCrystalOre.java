package ploiu.elementalitems.blocks.ores;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashSet;

import static net.minecraft.block.Blocks.NETHERRACK;

public class NetherCrystalOre extends FireCrystalOre {
	public NetherCrystalOre() {
		super("ore_fire_nether");
		this.blocksThisCanGenerateOver = Arrays.asList(
				NETHERRACK
		);
		this.biomesToGenerateIn = new HashSet<>();/*this.getBiomesToGenerateInFromTypes(
				BiomeDictionary.Type.NETHER
		);*/
		// this.biomesToGenerateIn.add(Biomes.NETHER);
	}

	

	/*@Override
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
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
		super.harvestBlock(worldIn, player, pos, state, te, stack);
		if(!player.abilities.isCreativeMode && this.RANDOM.nextInt(10) == 1) {
			worldIn.setBlockState(pos, Blocks.LAVA.getDefaultState());
		}
	}*/
}
