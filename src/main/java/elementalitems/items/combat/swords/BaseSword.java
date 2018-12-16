package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import elementalitems.items.ElementalItem;
import elementalitems.items.ItemHandler;
import elementalitems.util.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * The type Base sword.
 */
public abstract class BaseSword extends ItemSword implements ElementalItem {
	/**
	 * Instantiates a new Base sword.
	 *
	 * @param material the material
	 * @param name     the name
	 * @param type     the type
	 */
	protected BaseSword(Item.ToolMaterial material, String name, ElementalType type) {
		super(material);
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.COMBAT);
		this.type = type;
		//add our item to the registration arraylist for easy registration
		ItemHandler.items.add(this);
	}

	/**
	 * Instantiates a new Base sword.
	 */
	protected BaseSword(){
		super(Item.ToolMaterial.DIAMOND);
	}

	/**
	 * Instantiates a new Base sword.
	 *
	 * @param type the type
	 */
	public BaseSword(ElementalType type) {
		this(Utils.getInstance().getToolMaterialFromElementalType(type), "sword_" + type.getTypeName(), type);
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
	 * The Name.
	 */
	protected String name;
	/**
	 * The Type.
	 */
	protected ElementalType type;

	/**
	 * Apply effect boolean.
	 *
	 * @param user   the user
	 * @param target the target
	 * @return the boolean
	 */
	protected abstract boolean applyEffect(EntityLivingBase user, EntityLivingBase target);

	/**
	 * Special effect.
	 *
	 * @param world  the world
	 * @param player the player
	 * @param hand   the hand
	 */
	protected abstract void specialEffect(World world, EntityPlayer player, EnumHand hand);

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		this.specialEffect(world, player, hand);
		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		this.applyEffect(attacker, target);
		return super.hitEntity(stack, target, attacker);
	}
}
