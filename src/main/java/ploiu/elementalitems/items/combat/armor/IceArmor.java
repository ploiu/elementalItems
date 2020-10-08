package ploiu.elementalitems.items.combat.armor;

import net.minecraft.enchantment.DepthStriderEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.util.EntityUtils;
import ploiu.elementalitems.util.ItemUtils;

import static net.minecraft.enchantment.Enchantments.FROST_WALKER;

public class IceArmor extends BaseArmorItem {
	public IceArmor(EquipmentSlotType slot) {
		super(ElementalTypes.ICE, slot);
	}

	@Override
	public void onUserHurt(ItemStack stack, World world, DamageSource source, LivingEntity wearer) {
		if(EntityUtils.isValidLivingEntity(source.getImmediateSource())) {
			int numberOfPiecesWorn = EntityUtils.getNumberOfElementalArmorForType(ElementalTypes.ICE, wearer);
			int potionEffectLevel = numberOfPiecesWorn - 1; // potion effect level is 0-based, so this is from lv 1 - 4
			LivingEntity attacker = (LivingEntity) source.getImmediateSource();
			attacker.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, potionEffectLevel));
			attacker.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, potionEffectLevel));
		}
	}

	@Override
	public void applyPassiveEffect(ItemStack stack, World world, LivingEntity wearer) {
		int amountOfIceArmorPieces = EntityUtils.getNumberOfElementalArmorForType(ElementalTypes.ICE, wearer);
		int potionEffectLevel = amountOfIceArmorPieces == 4 ? 1 : 0;
		wearer.addPotionEffect(new EffectInstance(Effects.SPEED, 100, potionEffectLevel, false, false));
		// if we have all 4 pieces of armor, enchant the boots with frost walker
		if(amountOfIceArmorPieces == 4 && ElementalItemsItemRegistry.iceBoots.equals(stack.getItem())) {
			ItemUtils.removeEnchantmentFromItem(stack, FROST_WALKER);
			stack.addEnchantment(FROST_WALKER, 2);
		} else if(stack.getItem().equals(ElementalItemsItemRegistry.iceBoots)) {
			ItemUtils.removeEnchantmentFromItem(stack, FROST_WALKER);
		}
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(ElementalItemsItemRegistry.iceBoots.equals(stack.getItem()) && enchantment instanceof DepthStriderEnchantment) {
			return false;
		} else {
			return super.canApplyAtEnchantingTable(stack, enchantment);
		}
	}
}
