package elementalitems.sharedeffects.combat;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public interface ISharedIceCombatEffect {
	default void slowAndWeakenTarget(EntityLivingBase user, EntityLivingBase target) {
		// afflict the target with weakness and slowness for 5 seconds
		target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 2, false, true));
		target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 2, false, true));
	}
}
