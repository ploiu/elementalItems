package elementalitems.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;


/**
 * The type Base block.
 */
public class BaseBlock extends Block {
	/**
	 * The Name.
	 */
	protected String name;
	/**
	 * The Is beacon block.
	 */
	protected boolean isBeaconBlock;

	/**
	 * Instantiates a new Base block.
	 *
	 * @param name          the name
	 * @param isBeaconBlock the is beacon block
	 */
	public BaseBlock(String name, boolean isBeaconBlock) {
		super(Material.ROCK);
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

	/**
	 * Instantiates a new Base block.
	 *
	 * @param name the name
	 */
	public BaseBlock(String name){
		this(name, false);
	}

	/**
	 * Create block item item.
	 *
	 * @param basedOff the based off
	 * @return the item
	 */
	public Item createBlockItem(BaseBlock basedOff) {
		ItemBlock itemBlock = new ItemBlock(basedOff);
		itemBlock.setRegistryName(basedOff.getRegistryName());
		return itemBlock;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, BlockPos thisPosition, BlockPos beaconPos) {
		return this.isBeaconBlock;
	}
}
