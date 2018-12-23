package elementalitems.blocks;

import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	public Item createBlockItem(@Nonnull BaseBlock basedOff) {
		ItemBlock itemBlock = new ItemBlock(basedOff);
		itemBlock.setRegistryName(basedOff.getRegistryName());
		return itemBlock;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public boolean isBeaconBase(IBlockAccess world, BlockPos thisPosition, BlockPos beaconPos) {
		return this.isBeaconBlock;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
		return this != iblockstate.getBlock();
	}
}
