package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import elementalitems.items.ElementalItem;
import elementalitems.items.ItemHandler;
import elementalitems.util.ElementalUtils;
import elementalitems.util.EntityUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * The type Base sword.
 */
public abstract class BaseSword extends ItemSword implements ElementalItem {
	/**
	 * The Name.
	 */
	protected final String name;
	/**
	 * The Type.
	 */
	protected final ElementalType type;

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
	 * Instantiates a new Base sword. This constructor is used in tests so don't delete it
	 */
	protected BaseSword() {
		super(Item.ToolMaterial.DIAMOND);
		this.name = "baseSword";
		this.type = ElementalType.PLAIN;
	}

	/**
	 * Instantiates a new Base sword.
	 *
	 * @param type the type
	 */
	public BaseSword(ElementalType type) {
		this(ElementalUtils.getInstance().getToolMaterialFromElementalType(type), "sword_" + type.getTypeName(), type);
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
	 * Apply effect boolean.
	 *
	 * @param user   the user
	 * @param target the target
	 * @return the boolean
	 */
	protected abstract boolean applyEffect(@Nonnull EntityLivingBase user, @Nonnull EntityLivingBase target);

	/**
	 * Special effect.
	 *
	 * @param world  the world
	 * @param player the player
	 */
	protected abstract void specialEffect(World world, EntityPlayer player);

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		this.specialEffect(world, player);
		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(EntityUtils.getInstance().isValidEntityLivingBase(target) && EntityUtils.getInstance().isValidEntityLivingBase(attacker)) {
			this.applyEffect(attacker, target);
		}
		return super.hitEntity(stack, target, attacker);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.type);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof BaseSword && ((BaseSword) other).getName().equals(this.name);
	}

	@Override
	public String toString() {
		return "BaseSword{" +
				       "name='" + this.name + '\'' +
				       ", type=" + this.type +
				       '}';
	}
}
