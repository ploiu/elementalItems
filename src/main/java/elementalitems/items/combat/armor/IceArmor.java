package elementalitems.items.combat.armor;

import elementalitems.ElementalType;
import elementalitems.items.ItemHandler;
import elementalitems.util.EntityUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import static net.minecraft.init.Enchantments.DEPTH_STRIDER;
import static net.minecraft.init.Enchantments.FROST_WALKER;

/**
 * The type Ice armor.
 */
public class IceArmor extends BaseArmor {

	/**
	 * Instantiates a new Ice armor.
	 *
	 * @param slot the slot
	 */
	public IceArmor(EntityEquipmentSlot slot) {
		super(ElementalType.ICE, slot);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		// make sure enchantment is not frost walker or depth strider
		if(enchantment.equals(FROST_WALKER) || enchantment.equals(DEPTH_STRIDER)) {
			return false;
		}
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}

	@Override
	public void onUserHurt(DamageSource damageSource, EntityLivingBase wearer) {
		// if the damage source is an EntityDamgeSource and not an EntityIndirectDamageSource, slow and weaken the attacker
		if(damageSource instanceof EntityDamageSource && !(damageSource instanceof EntityDamageSourceIndirect) && EntityUtils.getInstance().isValidEntityLivingBase(damageSource.getImmediateSource())) {
			// get the damage causer
			EntityLivingBase causer = (EntityLivingBase) damageSource.getImmediateSource();
			int effectMultiplier = this.getNumberOfEquippedArmorPiecesOfThisElementalType(wearer);
			// apply slowness and weakness level mult - 1 for mult * 2 seconds (each second is 20 ticks)
			causer.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, effectMultiplier * 40, effectMultiplier - 1, false, true));
			causer.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, effectMultiplier * 40, effectMultiplier - 1, false, true));
		}
	}

	@Override
	public void applyPassiveEffect(EntityLivingBase wearer) {
		int setCount = this.getNumberOfEquippedArmorPiecesOfThisElementalType(wearer);
		// apply speed for (amountWearing -1) / 2
		int speedLevel = (setCount - 1) / 2;
		wearer.addPotionEffect(new PotionEffect(MobEffects.SPEED, 21, speedLevel, false, false));
		// the player's boots
		ItemStack wearerBoots = wearer.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		boolean areWearerBootsIceBoots = ItemHandler.iceBoots.equals(wearerBoots.getItem());
		// if we have a full set
		if(setCount == 4) {
			// we know that this item is the ice boots, so give our boots the frost walker enchantment
			if(areWearerBootsIceBoots && !EnchantmentHelper.hasFrostWalkerEnchantment(wearer)) {
				wearerBoots.addEnchantment(Enchantments.FROST_WALKER, 2);
			}
		} else if(areWearerBootsIceBoots) {
			this.removeFrostWalkerFromSelf(wearerBoots, wearer);
		}
	}

	private void removeFrostWalkerFromSelf(ItemStack toRemove, EntityLivingBase wearer) {
		if(EnchantmentHelper.hasFrostWalkerEnchantment(wearer)) {
			// get our enchantment tags, find the one with the ID that matches frost walker, and remove it
			int frostWalkerIndex = 0;
			NBTTagList enchantments = toRemove.getEnchantmentTagList();
			for(int i = 0; i < enchantments.tagCount(); i++) {
				if(FROST_WALKER.equals(Enchantment.getEnchantmentByID(enchantments.getCompoundTagAt(i).getShort("id")))) {
					frostWalkerIndex = i;
					break;
				}
			}
			// remove the enchantment at frostWalkerIndex
			enchantments.removeTag(frostWalkerIndex);
			// write the tag list to toRemove's enchantment list
			toRemove.setTagInfo("ench", enchantments);
		}
	}
}
