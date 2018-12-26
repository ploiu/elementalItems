package elementalitems.sharedeffects.combat;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;

public interface ISharedAirEffect {

	default void launchTarget(EntityLivingBase user, EntityLivingBase target) {
		target.knockBack(target, 2F, user.posX - target.posX, user.posZ - target.posZ);
		// launch it into the air, based on the target's knockBack resistance
		target.addVelocity(0, target.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue() + 1, 0);
	}
}
