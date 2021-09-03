package ploiu.elementalitems.items.combat.armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.combat.ElementalEffects;
import ploiu.elementalitems.util.EntityUtils;

public class EnderArmor extends BaseArmorItem {
	public EnderArmor(EquipmentSlotType slot) {
		super(ElementalTypes.ENDER, slot);
	}

	@Override
	public void onUserHurt(ItemStack stack, World world, DamageSource source, LivingEntity wearer) {
		// randomly teleport the attacker
		if(EntityUtils.isValidLivingEntity(source.getDirectEntity())) {
			LivingEntity attacker = (LivingEntity) source.getDirectEntity();
			ElementalEffects.teleportTarget(attacker);
		}
	}

	@Override
	public void applyPassiveEffect(ItemStack stack, World world, LivingEntity wearer) {
		// get the number of pieces this armor has
		int numberOfPieces = EntityUtils.getNumberOfElementalArmorForType(ElementalTypes.ENDER, wearer);
		if(numberOfPieces >= 1) {
			// give jump boost
			wearer.addEffect(new EffectInstance(Effects.JUMP, 21, 3, false, false));
		}
		if(numberOfPieces >= 2) {
			// apply night vision; the duration is so high because when it starts to run out the screen starts to flicker and it's annoying
			wearer.addEffect(new EffectInstance(Effects.NIGHT_VISION, 240, 0, false, false));
		}
		if(numberOfPieces >= 3) {
			// speed
			wearer.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 21, 0, false, false));
		}
		if(numberOfPieces == 4) {
			// luck
			wearer.addEffect(new EffectInstance(Effects.LUCK, 21, 1, false, false));
		}
	}
}
