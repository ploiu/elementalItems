package ploiu.elementalitems.items.combat.armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

/**
 * TODO prevent frost walker enchantment from being applied, explosion immunity, less arrow damage
 */
public class WaterArmor extends BaseArmorItem {
	public WaterArmor(EquipmentSlotType slot) {
		super(ElementalTypes.WATER, slot);
	}

	@Override
	public void onUserHurt(ItemStack stack, World world, DamageSource source, LivingEntity wearer) {
		// no-op since handling explosions is done differently
	}

	@Override
	public void applyPassiveEffect(ItemStack stack, World world, LivingEntity wearer) {
		// TODO enchant stuff and remove water shader depending on which pieces are worn
	}
}
