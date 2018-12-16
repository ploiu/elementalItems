package elementalitems.items.combat.armor;

import elementalitems.ElementalType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;

/**
 * The type Earth armor.
 */
public class EarthArmor extends BaseArmor {

	/**
	 * Instantiates a new Earth armor.
	 *
	 * @param slot the slot
	 */
	public EarthArmor(EntityEquipmentSlot slot) {
		super(ElementalType.EARTH, slot);
	}

	@Override
	public void onUserHurt(DamageSource damageSource, EntityLivingBase wearer) {
		// no op
	}

	@Override
	public void applyPassiveEffect(EntityLivingBase wearer) {
		// no op
	}
}
