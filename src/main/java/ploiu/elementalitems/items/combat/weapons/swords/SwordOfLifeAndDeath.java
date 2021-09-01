package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.tools.itemtiers.TierRegistry;
import ploiu.elementalitems.util.EntityUtils;

import javax.annotation.Nonnull;

public class SwordOfLifeAndDeath extends BaseSword {
	public SwordOfLifeAndDeath() {
		super(TierRegistry.plainTier, "sword_life_death", ElementalTypes.PLAIN);
	}

	@Override
	protected void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		// no-op
	}

	@Override
	public void onUsed(World world, PlayerEntity player, Hand hand) {
		// no-op
	}

	@Override
	public void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
		if(EntityUtils.isValidLivingEntity(target)) {
			// get the player's current health, and factor it into how much extra damage to deal
			float extraDamage = this.calculateExtraDamageBasedOnHealth(user);
			// dish out that extra damage
			target.hurt(DamageSource.OUT_OF_WORLD, extraDamage);
			// don't dish it if you can't take it (back into your own health)
			user.heal(extraDamage);
		}
	}

	/**
	 * calculates the extra damage done by this weapon based on the passed-in {@link LivingEntity}'s current health
	 *
	 * @param user the user of this weapon
	 * @return the amount of extra damage to deal to the mob being attacked with this weapon
	 */
	private float calculateExtraDamageBasedOnHealth(@Nonnull LivingEntity user) {
		// should be (user's max health - user's current health) * .75
		return (user.getMaxHealth() - user.getHealth()) * 0.75f;
	}
}
