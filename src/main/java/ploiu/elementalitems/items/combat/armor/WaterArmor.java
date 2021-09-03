package ploiu.elementalitems.items.combat.armor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.util.EntityUtils;
import ploiu.elementalitems.util.ItemUtils;

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
		// if the player is wearing a full set, enchant the helmet and boots with water-related enchantments
		int piecesWorn = EntityUtils.getNumberOfElementalArmorForType(ElementalTypes.WATER, wearer);
		if(piecesWorn == 4) {
			// if the player is in water, allow them to breathe forever
			if(wearer.isInWater()) {
				wearer.setAirSupply(300);
				// if the player has the mining fatigue effect (from elder guardians), remove it
				wearer.removeEffect(Effects.DIG_SLOWDOWN);
			}
			if(ElementalItemsItemRegistry.waterHelmet.equals(stack.getItem())) {
				ItemUtils.removeEnchantmentFromItem(stack, Enchantments.AQUA_AFFINITY);
				stack.enchant(Enchantments.AQUA_AFFINITY, 4);
			} else if(ElementalItemsItemRegistry.waterBoots.equals(stack.getItem())) {
				ItemUtils.removeEnchantmentFromItem(stack, Enchantments.DEPTH_STRIDER);
				// re-apply depth strider now
				stack.enchant(Enchantments.DEPTH_STRIDER, 3);
			}
		} else {
			// remove the enchantments as they are reserved for set bonus only
			if(ElementalItemsItemRegistry.waterHelmet.equals(stack.getItem())) {
				ItemUtils.removeEnchantmentFromItem(stack, Enchantments.AQUA_AFFINITY);
			} else if(ElementalItemsItemRegistry.waterBoots.equals(stack.getItem())) {
				ItemUtils.removeEnchantmentFromItem(stack, Enchantments.DEPTH_STRIDER);
			}
		}
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(ElementalItemsItemRegistry.waterBoots.equals(stack.getItem()) && enchantment instanceof FrostWalkerEnchantment) {
			return false;
		} else {
			return super.canApplyAtEnchantingTable(stack, enchantment);
		}
	}
}
