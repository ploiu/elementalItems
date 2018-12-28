package elementalitems.items.combat.armor;

import elementalitems.ElementalTypes;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

/**
 * The type Leaf armor.
 */
public class LeafArmor extends BaseArmor {

	/**
	 * Instantiates a new Leaf armor.
	 *
	 * @param slot the slot
	 */
	public LeafArmor(EntityEquipmentSlot slot) {
		super(ElementalTypes.LEAF, slot);
	}

	@Override
	public void onUserHurt(DamageSource damageSource, EntityLivingBase wearer) {
		// check if the damage source is an EntityDamageSource and the entity is valid
		if(damageSource instanceof EntityDamageSource && EntityUtils.getInstance().isValidEntityLivingBase(damageSource.getImmediateSource())) {
			// get if the attacker is undead
			EntityLivingBase attacker = (EntityLivingBase) damageSource.getImmediateSource();
			if(attacker.isEntityUndead()) {
				int effectMultiplier = this.getNumberOfEquippedArmorPiecesOfThisElementalType(wearer);
				// attack the entity for 1 health * multiplier (this damage increases exponentially with each additional piece, making a total of 16 damage with all 4 pieces)
				attacker.attackEntityFrom(DamageSource.MAGIC, effectMultiplier * effectMultiplier);
			}
		}
	}

	@Override
	public void applyPassiveEffect(EntityLivingBase wearer) {
		// apply regen level = player xp / 10 - 1, with a max of 2
		int regenLevel = EntityUtils.getInstance().getPlayerLevel(wearer) / 10 - 1;
		regenLevel = Math.min(Math.max(regenLevel, 0), 2);
		wearer.removePotionEffect(MobEffects.WITHER);
		wearer.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, regenLevel, false, false));
	}
}
