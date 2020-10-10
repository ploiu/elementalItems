package ploiu.elementalitems.items.combat.armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.util.EntityUtils;

public class FireArmor extends BaseArmorItem {
	public FireArmor(EquipmentSlotType slot) {
		super(ElementalTypes.FIRE, slot);
	}

	@Override
	public void onUserHurt(ItemStack stack, World world, DamageSource source, LivingEntity wearer) {
		if(EntityUtils.isValidLivingEntity(source.getImmediateSource())) {
			// get the number of fire armor pieces the wearer is wearing and set the attacker on fire for time based on that
			int numberOfFirePieces = EntityUtils.getNumberOfElementalArmorForType(ElementalTypes.FIRE, wearer);
			int fireTime = 5 * numberOfFirePieces;
			source.getImmediateSource().setFire(fireTime);
		}
	}

	@Override
	public void applyPassiveEffect(ItemStack stack, World world, LivingEntity wearer) {
		wearer.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 100, 0, false, false));
	}
}
