package elementalitems.items.tools.shovels;

import elementalitems.ElementalType;
import elementalitems.items.ElementalItem;
import elementalitems.items.ItemHandler;
import elementalitems.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Base shovel.
 */
public abstract class BaseShovel extends ItemSpade implements ElementalItem {
	/**
	 * The Name.
	 */
	protected String name;
	/**
	 * The Type.
	 */
	protected ElementalType type;

	private BaseShovel(Item.ToolMaterial material, String name, ElementalType type) {
		super(material);
		this.name = name;
		this.type = type;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.TOOLS);
		// add this item to the item handler for easy registration
		ItemHandler.items.add(this);
	}

	/**
	 * Instantiates a new Base shovel.
	 *
	 * @param type the type
	 */
	public BaseShovel(ElementalType type) {
		this(Utils.getInstance().getToolMaterialFromElementalType(type), "shovel_" + type.getTypeName(), type);
	}


	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ElementalType getType() {
		return this.type;
	}

	/**
	 * Apply effect.
	 *
	 * @param world    the world
	 * @param state    the state
	 * @param position the position
	 * @param user     the user
	 */
	protected abstract void applyEffect(World world, IBlockState state, BlockPos position, EntityLivingBase user);

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase user) {
		if(stack.canHarvestBlock(state) || "shovel".equals(state.getBlock().getHarvestTool(state))) {
			this.applyEffect(world, state, pos, user);
		}
		return super.onBlockDestroyed(stack, world, state, pos, user);
	}
}
