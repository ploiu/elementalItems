package elementalitems.sharedeffects.combat;

import net.minecraft.entity.EntityLivingBase;

public interface ISharedFireEffect {

	default void ignite(EntityLivingBase target) {
		// set the target on fire
		target.setFire(10);
	}
}
