package elementalitems.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The type Base block.
 */
public class BaseBlock extends BlockBreakable {
	/**
	 * The Name.
	 */
	protected String name;
	/**
	 * The Is beacon block.
	 */
	protected boolean isBeaconBlock;

	// the list of blocks this can replace during oreGen
	protected List<Block> blocksThisCanGenerateOver;

	// the list of biomes this block can generate in
	protected Set<Biome> biomesToGenerateIn;

	// the max and min y for generation
	protected int minYGeneration = 0;
	protected int maxYGeneration = 255;
	// the max vein size
	protected int maxVeinSize = 5;
	// the max chances per chunk this can spawn
	protected int spawnChances = 50;

	/**
	 * Instantiates a new Base block.
	 *
	 * @param name          the name
	 * @param isBeaconBlock the is beacon block
	 */
	public BaseBlock(String name, boolean isBeaconBlock) {
		this(name, isBeaconBlock, Material.ROCK);
	}

	public BaseBlock(String name, boolean isBeaconBlock, Material blockMaterial) {
		super(blockMaterial, false);
		this.name = name;
		this.isBeaconBlock = isBeaconBlock;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setHardness(2.0f);
		this.setHarvestLevel("pickaxe", 2);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.blocksThisCanGenerateOver = new ArrayList<>();
		this.biomesToGenerateIn = new HashSet<>();
		//add ourselves to the BlockHandler's list
		BlockHandler.blocks.add(this);
	}

	public BaseBlock(String name, Material blockMaterial) {
		this(name, false, blockMaterial);
	}

	/**
	 * Instantiates a new Base block.
	 *
	 * @param name the name
	 */
	public BaseBlock(String name) {
		this(name, false);
	}

	/**
	 * Create block item item.
	 *
	 * @param basedOff the based off
	 * @return the item
	 */
	public Item createBlockItem(@Nonnull  BaseBlock basedOff) {
		ItemBlock itemBlock = new ItemBlock(basedOff);
		itemBlock.setRegistryName(basedOff.getRegistryName());
		return itemBlock;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, BlockPos thisPosition, BlockPos beaconPos) {
		return this.isBeaconBlock;
	}

	public Predicate<IBlockState> getGeneratorPredicate() {
		return input -> input != null && this.blocksThisCanGenerateOver.contains(input.getBlock());
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

	public int getMaxVeinSize() {
		return this.maxVeinSize;
	}

	public int getSpawnChances() {
		return this.spawnChances;
	}
}
