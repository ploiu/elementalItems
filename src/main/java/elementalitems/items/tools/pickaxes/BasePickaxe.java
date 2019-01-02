package elementalitems.items.tools.pickaxes;

import elementalitems.ElementalTypes;
import elementalitems.items.ElementalItem;
import elementalitems.items.ItemHandler;
import elementalitems.items.tools.BaseTool;
import elementalitems.util.ElementalUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

import java.util.Objects;

/**
 * The type Base pickaxe.
 */
public abstract class BasePickaxe extends ItemPickaxe implements ElementalItem, BaseTool {
	/**
	 * The Name.
	 */
	protected final String name;
	/**
	 * The Type.
	 */
	protected final ElementalTypes type;

	private BasePickaxe(Item.ToolMaterial material, String name, ElementalTypes type) {
		super(material);
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.type = type;
		//add our item to the registration arraylist for easy registration
		ItemHandler.items.add(this);
	}

	/**
	 * Instantiates a new Base pickaxe.
	 *
	 * @param type the type
	 */
	public BasePickaxe(ElementalTypes type) {
		this(ElementalUtils.getInstance().getToolMaterialFromElementalType(type), "pickaxe_" + type.getTypeName(), type);
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
		if(stack.canHarvestBlock(state)) {
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
		return other instanceof BasePickaxe && ((BasePickaxe) other).getName().equals(this.name);
	}

	@Override
	public String toString() {
		return "BasePickaxe{" +
				       "name='" + this.name + '\'' +
				       ", type=" + this.type +
				       '}';
	}
}
