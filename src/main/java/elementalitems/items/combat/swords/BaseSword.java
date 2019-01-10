package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.items.ElementalItem;
import elementalitems.items.ItemHandler;
import elementalitems.util.ElementalUtils;
import elementalitems.util.EntityUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
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
	protected final ElementalTypes type;
	// the tooltip that will render when the sword is hovered over
	protected String tooltip;

	/**
	 * Instantiates a new Base sword.
	 *
	 * @param material the material
	 * @param name     the name
	 * @param type     the type
	 */
	protected BaseSword(Item.ToolMaterial material, String name, ElementalTypes type) {
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
		this.type = ElementalTypes.PLAIN;
	}

	/**
	 * Instantiates a new Base sword.
	 *
	 * @param type the type
	 */
	public BaseSword(ElementalTypes type) {
		this(ElementalUtils.getInstance().getToolMaterialFromElementalType(type), "sword_" + type.getTypeName(), type);
	}

	public BaseSword setTooltip(String tooltip) {
		this.tooltip = tooltip;
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ElementalTypes getType() {
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
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if(this.tooltip != null) {
			tooltip.add(this.tooltip);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if(EntityUtils.getInstance().isValidEntityLivingBase(target) && EntityUtils.getInstance().isValidEntityLivingBase(attacker)) {
			if(target.getEntityWorld() instanceof WorldServer){
				WorldServer worldServer = (WorldServer)target.getEntityWorld();
				this.spawnAttackParticles(worldServer, target);
			}
			this.applyEffect(attacker, target);
		}
		return super.hitEntity(stack, target, attacker);
	}

	protected void spawnAttackParticles(WorldServer worldServer, EntityLivingBase targetToSpawnParticlesAt) {
		Map<EnumParticleTypes, Integer> particlesToSpawn = ElementalUtils.getInstance().getParticlesForElementalType(this.type);
		// spawn all the particle types associated with this
		particlesToSpawn.forEach((type, count) -> {
			// use the worldServer to spawn the particles
			worldServer.spawnParticle(type, false, targetToSpawnParticlesAt.posX, targetToSpawnParticlesAt.posY, targetToSpawnParticlesAt.posZ, count, targetToSpawnParticlesAt.width, targetToSpawnParticlesAt.height, targetToSpawnParticlesAt.width, 0.0);
		});
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
