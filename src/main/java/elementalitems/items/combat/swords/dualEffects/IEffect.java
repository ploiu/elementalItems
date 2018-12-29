package elementalitems.items.combat.swords.dualEffects;

import net.minecraft.entity.EntityLivingBase;

@FunctionalInterface
public interface IEffect {
	void apply(EntityLivingBase user, EntityLivingBase target);
}
