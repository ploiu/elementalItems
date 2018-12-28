package elementalitems.items.combat.armor;

import elementalitems.ElementalTypes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;

/**
 * The type Plain armor.
 */
public class PlainArmor extends BaseArmor {

	/**
	 * Instantiates a new Plain armor.
	 *
	 * @param slot the slot
	 */
	public PlainArmor(EntityEquipmentSlot slot) {
		super(ElementalTypes.PLAIN, slot);
	}

	@Override
	public void onUserHurt(DamageSource damageSource, EntityLivingBase wearer) {

	}

	@Override
	public void applyPassiveEffect(EntityLivingBase wearer) {

	}
}
