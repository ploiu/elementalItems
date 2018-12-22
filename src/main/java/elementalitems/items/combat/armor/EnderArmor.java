package elementalitems.items.combat.armor;

import elementalitems.ElementalType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

/**
 * The type Ender armor.
 */
public class EnderArmor extends BaseArmor {

	/**
	 * Instantiates a new Ender armor.
	 *
	 * @param slot the slot
	 */
	public EnderArmor(EntityEquipmentSlot slot) {
		super(ElementalType.ENDER, slot);
	}

	@Override
	public void onUserHurt(DamageSource damageSource, EntityLivingBase wearer) {
		// TODO
	}

	@Override
	public void applyPassiveEffect(EntityLivingBase wearer) {
		// get the number of pieces this armor has
		int numberOfPieces = this.getNumberOfEquippedArmorPiecesOfThisElementalType(wearer);
		if(numberOfPieces >= 1) {
			// give jump boost
			wearer.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 21, 1, false, false));
		}
		if(numberOfPieces >= 2) {
			// apply night vision; the duration is so high because when it starts to run out the screen starts to flicker and it's annoying
			wearer.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 240, 0, false, false));
		}
		if(numberOfPieces >= 3) {
			// speed
			wearer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 21, 0, false, false));
		}
		if(numberOfPieces == 4) {
			// luck
			wearer.addPotionEffect(new PotionEffect(MobEffects.LUCK, 21, 1, false, false));
		}
	}
}
