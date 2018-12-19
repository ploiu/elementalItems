package elementalitems.blocks;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;


/**
 * The type Base block.
 */
public class BaseBlock extends BlockBreakable {
	/**
	 * The Name.
	 */
	protected final String name;
	/**
	 * The Is beacon block.
	 */
	protected final boolean isBeaconBlock;


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
		//add ourselves to the BlockHandler's list
		BlockHandler.blocks.add(this);
	}

	public BaseBlock(String name, Material blockMaterial) {
		this(name, false, blockMaterial);
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
	
	@Override
	public String toString(){
		return this.name;
	}

}
