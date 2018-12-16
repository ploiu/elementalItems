package elementalitems.items.combat.armor;

import elementalitems.ElementalType;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;

/**
 * The type Air armor.
 */
public class AirArmor extends BaseArmor {

	/**
	 * Instantiates a new Air armor.
	 *
	 * @param slot the slot
	 */
	public AirArmor(EntityEquipmentSlot slot) {
		super(ElementalType.AIR, slot);
	}

	@Override
	public void onUserHurt(DamageSource damageSource, EntityLivingBase wearer) {
		// get the damage source entity and validate it
		if(EntityUtils.getInstance().isValidEntityLivingBase(damageSource.getImmediateSource())) {
			EntityLivingBase attacker = (EntityLivingBase) damageSource.getImmediateSource();
			// knockback the attacker based on the number of pieces being worn and the attacker's knockback resistance
			float effectMultiplier = this.getNumberOfEquippedArmorPiecesOfThisElementalType(wearer) / 4f;
			// knockback the attacker
			attacker.knockBack(wearer, effectMultiplier, (double) -MathHelper.sin(attacker.rotationYaw * 0.017453292F), (double) (MathHelper.cos(attacker.rotationYaw * 0.017453292F)));
		}
	}

	@Override
	public void applyPassiveEffect(EntityLivingBase wearer) {
		// no op
	}
}
