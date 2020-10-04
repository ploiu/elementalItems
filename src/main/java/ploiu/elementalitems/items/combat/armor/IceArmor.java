package ploiu.elementalitems.items.combat.armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class IceArmor extends BaseArmorItem {
	public IceArmor(EquipmentSlotType slot) {
		super(ElementalTypes.ICE, slot);
	}

	@Override
	public void onUserHurt(ItemStack stack, World world, DamageSource source, LivingEntity target) {
		// TODO slow and weaken the target
	}

	@Override
	public void applyPassiveEffect(ItemStack stack, World world, LivingEntity wearer) {
		// TODO apply speed
	}
}
