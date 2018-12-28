package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.util.ElementalUtils;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import javax.annotation.Nonnull;


/**
 * The type Life death sword.
 */
public class LifeDeathSword extends BaseSword {

	/**
	 * Instantiates a new Life death sword.
	 */
	public LifeDeathSword() {
		super(ElementalUtils.getInstance().getToolMaterialFromElementalType(ElementalTypes.PLAIN), "sword_life_death", ElementalTypes.PLAIN);
	}

	/**
	 * Instantiates a new Life death sword.
	 *
	 * @param material the material
	 * @param name     the name
	 * @param type     the type
	 */
	public LifeDeathSword(Item.ToolMaterial material, String name, ElementalTypes type) {
	}

	@Override
	protected boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		if(EntityUtils.getInstance().isValidEntityLivingBase(target)) {
			// get the player's current health, and factor it into how much extra damage to deal
			float extraDamage = this.calculateExtraDamageBasedOnHealth(user);
			// dish out that extra damage
			target.attackEntityFrom(DamageSource.OUT_OF_WORLD, extraDamage);
			// don't dish it if you can't take it (back into your own health)
			user.heal(extraDamage);
		}
		return false;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player) {
		// no op
	}

	/**
	 * calculates the extra damage done by this weapon based on the passed-in {@link EntityLivingBase}'s current health
	 *
	 * @param user the user of this weapon
	 * @return the amount of extra damage to deal to the mob being attacked with this weapon
	 */
	private float calculateExtraDamageBasedOnHealth(@Nonnull EntityLivingBase user) {
		// should be (user's max health - user's current health) * .75
		return (user.getMaxHealth() - user.getHealth()) * 0.75f;
	}
}
