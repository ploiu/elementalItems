package ploiu.elementalitems.items.combat.armor;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.util.EntityUtils;

/**
 * TODO apply regeneration, hurt undead that attack player, reduce wither and poison debuffs
 */
public class LeafArmor extends BaseArmorItem {
	public LeafArmor(EquipmentSlotType slot) {
		super(ElementalTypes.LEAF, slot);
	}

	@Override
	public void onUserHurt(ItemStack stack, World world, DamageSource source, LivingEntity wearer) {
		if(EntityUtils.isValidLivingEntity(source.getImmediateSource())) {
			LivingEntity attacker = (LivingEntity) source.getImmediateSource();
			if(attacker.getCreatureAttribute() == CreatureAttribute.UNDEAD) {
				// deal 2.5 hearts of damage times the number of pieces of this armor worn
				attacker.hurt(DamageSource.GENERIC, 5 * EntityUtils.getNumberOfElementalArmorForType(ElementalTypes.LEAF, wearer));
			}
		}
	}

	@Override
	public void applyPassiveEffect(ItemStack stack, World world, LivingEntity wearer) {
		// give regeneration and remove the wither potion effect
		if(EntityUtils.isValidLivingEntity(wearer)) {
			wearer.removePotionEffect(Effects.WITHER);
			int regenLevel = EntityUtils.getNumberOfElementalArmorForType(ElementalTypes.LEAF, wearer);
			wearer.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100, regenLevel - 1, false, true));
		}
	}
}
