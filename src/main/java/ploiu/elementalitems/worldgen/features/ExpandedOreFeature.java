package ploiu.elementalitems.worldgen.features;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

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
public class ExpandedOreFeature extends Feature<ExpandedOreFeatureConfig> {
	public ExpandedOreFeature(Function<Dynamic<?>, ? extends ExpandedOreFeatureConfig> p_i51472_1_) {
		super(p_i51472_1_);
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, ExpandedOreFeatureConfig config) {
		float f = rand.nextFloat() * (float) Math.PI;
		float f1 = (float) config.size / 8.0F;
		int i = MathHelper.ceil(((float) config.size / 16.0F * 2.0F + 1.0F) / 2.0F);
		double d0 = (double) ((float) pos.getX() + MathHelper.sin(f) * f1);
		double d1 = (double) ((float) pos.getX() - MathHelper.sin(f) * f1);
		double d2 = (double) ((float) pos.getZ() + MathHelper.cos(f) * f1);
		double d3 = (double) ((float) pos.getZ() - MathHelper.cos(f) * f1);
		int j = 2;
		double d4 = (double) (pos.getY() + rand.nextInt(3) - 2);
		double d5 = (double) (pos.getY() + rand.nextInt(3) - 2);
		int k = pos.getX() - MathHelper.ceil(f1) - i;
		int l = pos.getY() - 2 - i;
		int i1 = pos.getZ() - MathHelper.ceil(f1) - i;
		int j1 = 2 * (MathHelper.ceil(f1) + i);
		int k1 = 2 * (2 + i);

		for(int l1 = k; l1 <= k + j1; ++l1) {
			for(int i2 = i1; i2 <= i1 + j1; ++i2) {
				return this.generateBlock(worldIn, rand, config, d0, d1, d2, d3, d4, d5, k, l, i1, j1, k1);
			}
		}

		return false;
	}

	protected boolean generateBlock(IWorld world, Random random, ExpandedOreFeatureConfig config, double xStart, double xEnd, double zStart, double zEnd, double yMin, double yMax, int x, int y, int z, int p_207803_19_, int p_207803_20_) {
		int i = 0;
		BitSet attemptedLocations = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
		double[] coordinateSets = new double[config.size * 4];
		/** BUILD COORDINATE SETS **/
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

		/** CHECK IF THE BLOCK SHOULD BE PLACED AND PLACE IT **/
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
					if(xCoordChance * xCoordChance < 1.0D) {
						for(int actualY = k3; actualY <= j1; ++actualY) {
							double yCoordChance = ((double) actualY + 0.5D - currentY) / coordinateContinueChance;
							if(yCoordChance * yCoordChance + xCoordChance * xCoordChance < 1.0D) {
								for(int actualZ = l; actualZ <= k1; ++actualZ) {
									double zCoordChance = ((double) actualZ + 0.5D - currentZ) / coordinateContinueChance;
									// if the coordinates all are less than 5, run one last check before generating the block
									if(xCoordChance * xCoordChance + yCoordChance * yCoordChance + zCoordChance * zCoordChance < 1.0D) {
										int k2 = actualX - x + (actualY - y) * p_207803_19_ + (actualZ - z) * p_207803_19_ * p_207803_20_;
										if(!attemptedLocations.get(k2)) {
											attemptedLocations.set(k2);
											blockpos$mutableblockpos.setPos(actualX, actualY, actualZ);
											if(generateBlock(world, blockpos$mutableblockpos, config)) {
												i++;
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

		return i > 0;
	}

	protected boolean generateBlock(IWorld worldIn, BlockPos blockPos, ExpandedOreFeatureConfig config) {
		if(config.target.getCanReplaceFunction().test(worldIn.getBlockState(blockPos))) {
			worldIn.setBlockState(blockPos, config.state, 2);
			return true;
		}
		return false;
	}
}