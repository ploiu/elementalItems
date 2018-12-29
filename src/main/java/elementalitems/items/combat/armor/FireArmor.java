package elementalitems.items.combat.armor;

import elementalitems.ElementalTypes;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

import javax.annotation.Nonnull;

/**
 * The type Fire armor.
 */
public class FireArmor extends BaseArmor {

	/**
	 * Instantiates a new Fire armor.
	 *
	 * @param equipmentSlot the equipment slot
	 */
	public FireArmor(EntityEquipmentSlot equipmentSlot) {
		super(ElementalTypes.FIRE, equipmentSlot);
	}

	@Override
	public void onUserHurt(DamageSource damageSource, EntityLivingBase wearer) {
		// make sure the damageSource is an EntityDamageSource and the attacking entity is valid
		if(damageSource instanceof EntityDamageSource) {
			// get the entity of the damageSource
			Entity damageSourceEntity = damageSource.getImmediateSource();
			// make sure the damageSourceEntity is valid
			if(EntityUtils.getInstance().isValidEntityLivingBase(damageSourceEntity)) {
				// set the entity on fire for 5 seconds
				damageSourceEntity.setFire(5 * this.getNumberOfEquippedArmorPiecesOfThisElementalType(wearer));
			}
		}
	}

	@Override
	public void applyPassiveEffect(@Nonnull EntityLivingBase wearer) {
		// fire proof
		if(this.getNumberOfEquippedArmorPiecesOfThisElementalType(wearer) == 4) {
			wearer.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 21, 0, false, false));
		}
	}
}
