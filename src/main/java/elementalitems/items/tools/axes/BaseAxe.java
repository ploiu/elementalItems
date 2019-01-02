package elementalitems.items.tools.axes;

import elementalitems.ElementalTypes;
import elementalitems.items.ElementalItem;
import elementalitems.items.ItemHandler;
import elementalitems.items.tools.BaseTool;
import elementalitems.util.ElementalUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

import java.util.Objects;

/**
 * The type Base axe.
 */
public abstract class BaseAxe extends ItemAxe implements ElementalItem, BaseTool {
	/**
	 * The Name.
	 */
	protected final String name;
	/**
	 * The Type.
	 */
	protected final ElementalTypes type;

	/**
	 * Instantiates a new Base axe.
	 *
	 * @param material the material
	 * @param name     the name
	 * @param type     the type
	 */
	public BaseAxe(Item.ToolMaterial material, String name, ElementalTypes type) {
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
	public BaseAxe(ElementalTypes type) {
		this(ElementalUtils.getInstance().getToolMaterialFromElementalType(type), "axe_" + type.getTypeName(), type);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ElementalTypes getType() {
		return this.type;
	}


	/*@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase user) {
		if("axe".equals(state.getBlock().getHarvestTool(state))) {
			this.applyEffect(world, state, pos, user);
		}

		return super.onBlockDestroyed(stack, world, state, pos, user);
	}*/

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
