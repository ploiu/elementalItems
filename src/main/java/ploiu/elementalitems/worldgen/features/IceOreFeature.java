package ploiu.elementalitems.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.BitSet;
import java.util.Random;

/**
 * Like {@link ExpandedOreFeatureConfig}, this class is the result of not being able to directly extend a class. (in this case it's {@link OreFeature}
 * as it references {@link OreFeatureConfig} and I can't extend that.
 * <p>
 * This whole thing is a mess and I wish I didn't have to do this. Basically this entire class is copied from {@link OreFeature}
 * but with the changed generic type.
 *
 * <h1 style="color: red">DO NOT TOUCH THE INNER CONTENTS OF THIS FILE. IT IS MEANT TO BEHAVE EXACTLY AS THE CLASS IT CANNOT EXTEND</h1>
 * <p>
 * I may be missing something that allows me to not do this. If I find that, this class will be removed.
 */
public class IceOreFeature extends ExpandedOreFeature {
	public IceOreFeature(Codec<ExpandedOreFeatureConfig> codec) {
		super(codec);
	}
	@Override
	protected boolean generateBlock(IWorld world, Random random, ExpandedOreFeatureConfig config, double xStart, double xEnd, double zStart, double zEnd, double yMin, double yMax, int x, int y, int z, int p_207803_19_, int p_207803_20_) {
		/*int i = 0;
		BitSet attemptedLocations = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
		BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();
		double[] coordinateSets = new double[config.size * 4];
		*//** BUILD COORDINATE SETS **//*
		for(int j = 0; j < config.size; ++j) {
			float f = (float) j / (float) config.size;
			double d0 = MathHelper.lerp((double) f, xStart, xEnd);
			double d2 = MathHelper.lerp((double) f, yMin, yMax);
			double d4 = MathHelper.lerp((double) f, zStart, zEnd);
			double d6 = random.nextDouble() * (double) config.size / 16.0D;
			double d7 = ((double) (MathHelper.sin((float) Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
			coordinateSets[j * 4 + 0] = d0;
			coordinateSets[j * 4 + 1] = d2;
			coordinateSets[j * 4 + 2] = d4;
			coordinateSets[j * 4 + 3] = d7;
		}

		for(int l2 = 0; l2 < config.size - 1; ++l2) {
			if(!(coordinateSets[l2 * 4 + 3] <= 0.0D)) {
				for(int j3 = l2 + 1; j3 < config.size; ++j3) {
					if(!(coordinateSets[j3 * 4 + 3] <= 0.0D)) {
						double d12 = coordinateSets[l2 * 4 + 0] - coordinateSets[j3 * 4 + 0];
						double d13 = coordinateSets[l2 * 4 + 1] - coordinateSets[j3 * 4 + 1];
						double d14 = coordinateSets[l2 * 4 + 2] - coordinateSets[j3 * 4 + 2];
						double d15 = coordinateSets[l2 * 4 + 3] - coordinateSets[j3 * 4 + 3];
						if(d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14) {
							if(d15 > 0.0D) {
								coordinateSets[j3 * 4 + 3] = -1.0D;
							} else {
								coordinateSets[l2 * 4 + 3] = -1.0D;
							}
						}
					}
				}
			}
		}

		*//** CHECK IF THE BLOCK SHOULD BE PLACED AND PLACE IT **//*
		for(int i3 = 0; i3 < config.size; ++i3) {
			double coordinateContinueChance = coordinateSets[i3 * 4 + 3];
			if(!(coordinateContinueChance < 0.0D)) {
				double currentX = coordinateSets[i3 * 4 + 0];
				double currentY = coordinateSets[i3 * 4 + 1];
				double currentZ = coordinateSets[i3 * 4 + 2];
				int k = Math.max(MathHelper.floor(currentX - coordinateContinueChance), x);
				int k3 = Math.max(MathHelper.floor(currentY - coordinateContinueChance), y);
				int l = Math.max(MathHelper.floor(currentZ - coordinateContinueChance), z);
				int i1 = Math.max(MathHelper.floor(currentX + coordinateContinueChance), k);
				int j1 = Math.max(MathHelper.floor(currentY + coordinateContinueChance), k3);
				int k1 = Math.max(MathHelper.floor(currentZ + coordinateContinueChance), l);

				for(int actualX = k; actualX <= i1; ++actualX) {
					double xCoordChance = ((double) actualX + 0.5D - currentX) / coordinateContinueChance;
					if(xCoordChance * xCoordChance < 1) {
						for(int actualY = k3; actualY <= j1; ++actualY) {
							double yCoordChance = ((double) actualY + 0.5D - currentY) / coordinateContinueChance;
							if(xCoordChance * xCoordChance + yCoordChance * yCoordChance < 1) {
								for(int actualZ = l; actualZ <= k1; ++actualZ) {
									double zCoordChance = ((double) actualZ + 0.5D - currentZ) / coordinateContinueChance;
									// if the coordinates all are less than 5, run one last check before generating the block
									if(xCoordChance * xCoordChance + yCoordChance * yCoordChance + zCoordChance * zCoordChance < 1.0D) {
										int k2 = actualX - x + (actualY - y) * p_207803_19_ + (actualZ - z) * p_207803_19_ * p_207803_20_;
										if(!attemptedLocations.get(k2)) {
											attemptedLocations.set(k2);
											blockpos$mutableblockpos.setPos(actualX, actualY, actualZ);
											if(config.target.getCanReplaceFunction().test(world.getBlockState(blockpos$mutableblockpos)) && world.getBlockState(blockpos$mutableblockpos.up()).getBlock().equals(Blocks.AIR)) {
												world.setBlock(blockpos$mutableblockpos, config.state, 2);
												++i;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		return i > 0;*/
		return false;
	}
}