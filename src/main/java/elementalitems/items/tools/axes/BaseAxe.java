package elementalitems.items.tools.axes;

import elementalitems.ElementalType;
import elementalitems.items.ElementalItem;
import elementalitems.items.ItemHandler;
import elementalitems.util.ElementalUtils;
import elementalitems.util.Utils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

/**
 * The type Base axe.
 */
public abstract class BaseAxe extends ItemAxe implements ElementalItem {
	/**
	 * The Name.
	 */
	protected final String name;
	/**
	 * The Type.
	 */
	protected final ElementalType type;

	/**
	 * Instantiates a new Base axe.
	 *
	 * @param material the material
	 * @param name     the name
	 * @param type     the type
	 */
	public BaseAxe(Item.ToolMaterial material, String name, ElementalType type) {
		super(material, material.getAttackDamage(), material.getEfficiency());
		this.name = name;
		this.type = type;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.TOOLS);
		//add our item to the registration arraylist for easy registration
		ItemHandler.items.add(this);
	}

	/**
	 * Instantiates a new Base axe.
	 *
	 * @param type the type
	 */
	public BaseAxe(ElementalType type) {
		this(ElementalUtils.getInstance().getToolMaterialFromElementalType(type), "axe_" + type.getTypeName(), type);
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
		if("axe".equals(state.getBlock().getHarvestTool(state))) {
			this.applyEffect(world, state, pos, user);
		}

		return super.onBlockDestroyed(stack, world, state, pos, user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.type);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof BaseAxe && ((BaseAxe) other).getName().equals(this.name);
	}

	@Override
	public String toString() {
		return "BaseAxe{" +
				       "name='" + this.name + '\'' +
				       ", type=" + this.type +
				       '}';
	}
}
