package ploiu.elementalitems.worldgen.features;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockMatcher;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static net.minecraft.block.Blocks.*;
import static net.minecraft.block.Blocks.DIRT;

/**
 * This class is meant to expand upon {@link OreFeatureConfig}, but can't do so directly because I would have to use the
 * value I'm trying to expand upon: {@link OreFeatureConfig.FillerBlockType}, which is an enum that is very underwhelming for my purposes.
 * <p>
 * As such, a lot of the code is directly copied so that I can retain current functionality but with some expanded stuff
 */
public class ExpandedOreFeatureConfig implements IFeatureConfig {
	public final ExpandedOreFeatureConfig.FillerBlockType target;
	public final int size;
	public final BlockState state;

	public ExpandedOreFeatureConfig(ExpandedOreFeatureConfig.FillerBlockType target, BlockState state, int size) {
		this.size = size;
		this.state = state;
		this.target = target;
	}

	public static ExpandedOreFeatureConfig deserialize(Dynamic<?> dynamic) {
		int veinSize = dynamic.get("size").asInt(0);
		ExpandedOreFeatureConfig.FillerBlockType orefeatureconfig$fillerblocktype = ExpandedOreFeatureConfig.FillerBlockType.getFillerBlockTypeByName(dynamic.get("target").asString(""));
		BlockState blockstate = dynamic.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
		return new ExpandedOreFeatureConfig(orefeatureconfig$fillerblocktype, blockstate, veinSize);
	}

	public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
		return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("size"), ops.createInt(this.size), ops.createString("target"), ops.createString(this.target.getName()), ops.createString("state"), BlockState.serialize(ops, this.state).getValue())));
	}

	public static enum FillerBlockType {
		/**
		 * VANILLA ENUM PARTS
		 **/
		NATURAL_STONE("natural_stone", (p_214739_0_) -> {
			if(p_214739_0_ == null) {
				return false;
			} else {
				Block block = p_214739_0_.getBlock();
				return block == Blocks.STONE || block == Blocks.GRANITE || block == Blocks.DIORITE || block == Blocks.ANDESITE;
			}
		}),
		NETHERRACK("netherrack", new BlockMatcher(Blocks.NETHERRACK)),
		/**
		 * MY ENUM PARTS
		 **/
		ICE_CRYSTAL("ice_crystal", (blockState) -> {
			if(blockState == null || blockState.getBlock() == null) {
				return false;
			} else {
				Block block = blockState.getBlock();
				return Blocks.ICE.equals(block) || Blocks.PACKED_ICE.equals(block) || Blocks.BLUE_ICE.equals(block) || Blocks.WATER.equals(block);
			}
		}),
		EARTH_CRYSTAL("earth_crystal", (blockState) -> {
			if(blockState == null || blockState.getBlock() == null) {
				return false;
			} else {
				final List<Block> blocks = Arrays.asList(
						STONE,
						COBBLESTONE,
						TERRACOTTA,
						BLACK_TERRACOTTA,
						WHITE_TERRACOTTA,
						ORANGE_TERRACOTTA,
						MAGENTA_TERRACOTTA,
						LIGHT_BLUE_TERRACOTTA,
						YELLOW_TERRACOTTA,
						LIME_TERRACOTTA,
						PINK_TERRACOTTA,
						GRAY_TERRACOTTA,
						LIGHT_GRAY_TERRACOTTA,
						CYAN_TERRACOTTA,
						PURPLE_TERRACOTTA,
						BLUE_TERRACOTTA,
						BROWN_TERRACOTTA,
						GREEN_TERRACOTTA,
						RED_TERRACOTTA,
						DIRT
				);
				boolean matches = false;
				Block stateBlock = blockState.getBlock();
				for(Block block : blocks) {
					if(stateBlock.equals(block)) {
						matches = true;
						break;
					}
				}
				return matches;
			}
		}),
		ENDER_CRYSTAL("ender_crystal", new BlockMatcher(END_STONE));

		private static final Map<String, ExpandedOreFeatureConfig.FillerBlockType> fillerBlockRegistry = Arrays.stream(values()).collect(Collectors.toMap(ExpandedOreFeatureConfig.FillerBlockType::getName, (fillerBlockType) -> {
			return fillerBlockType;
		}));
		private final String name;
		private final Predicate<BlockState> canReplaceFunction;

		private FillerBlockType(String name, Predicate<BlockState> canReplaceFunction) {
			this.name = name;
			this.canReplaceFunction = canReplaceFunction;
		}

		public static ExpandedOreFeatureConfig.FillerBlockType getFillerBlockTypeByName(String name) {
			return fillerBlockRegistry.get(name);
		}

		public String getName() {
			return this.name;
		}

		public Predicate<BlockState> getCanReplaceFunction() {
			return this.canReplaceFunction;
		}
	}
}
