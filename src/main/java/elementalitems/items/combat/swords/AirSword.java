package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * The type Air sword.
 */
public class AirSword extends BaseSword {

	/**
	 * Instantiates a new Air sword.
	 */
	public AirSword() {
		super(ElementalType.AIR);
	}

	@Override
	protected boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		if(EntityUtils.getInstance().isValidEntityLivingBase(target)) {
			target.knockBack(target, 2F, user.posX - target.posX, user.posZ - target.posZ);
			// launch it into the air, based on the target's knockBack resistance
			target.addVelocity(0, target.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue() + 1, 0);
		}

		return true;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player, EnumHand hand) {
		// no op
	}

}
