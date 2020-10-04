package ploiu.elementalitems.items.combat.armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

/**
 * TODO remove teleport damage, teleport direct attackers, potion effects based on pieces worn
 */
public class EnderArmor extends BaseArmorItem {
	public EnderArmor(EquipmentSlotType slot) {
		super(ElementalTypes.ENDER, slot);
	}

	@Override
	public void onUserHurt(ItemStack stack, World world, DamageSource source, LivingEntity wearer) {

	}

	@Override
	public void applyPassiveEffect(ItemStack stack, World world, LivingEntity wearer) {

	}
}
