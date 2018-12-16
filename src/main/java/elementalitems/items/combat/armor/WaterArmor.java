package elementalitems.items.combat.armor;

import elementalitems.ElementalType;
import elementalitems.items.ItemHandler;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;

import javax.annotation.Nonnull;

import static net.minecraft.init.Enchantments.DEPTH_STRIDER;
import static net.minecraft.init.Enchantments.FROST_WALKER;

/**
 * The type Water armor.
 */
public class WaterArmor extends BaseArmor {

	/**
	 * Instantiates a new Water armor.
	 *
	 * @param slot the slot
	 */
	public WaterArmor(EntityEquipmentSlot slot) {
		super(ElementalType.WATER, slot);
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
		// no op; handled as an event
	}

	@Override
	public void applyPassiveEffect(EntityLivingBase wearer) {
		// this guard allows us to skip null checks and checks for elemental type since it verifies that the wearer is wearing a full set of this armor
		ItemStack wearerBoots = wearer.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		if(this.getNumberOfEquippedArmorPiecesOfThisElementalType(wearer) == 4) {
			if(wearer.isInWater()) {
				// keep the wearer from drowning
				wearer.setAir(300);
				// apply depth strider lv 3 to the boots if it doesn't already have it
				if(EnchantmentHelper.getDepthStriderModifier(wearer) <= 0) {
					wearerBoots.addEnchantment(DEPTH_STRIDER, 3);
				}
			} else {
				// remove depthStrider
				this.removeDepthStriderFromSelf(wearerBoots, wearer);
			}
		} else if(wearerBoots.getItem().equals(ItemHandler.waterBoots)) {
			// still remove depth strider
			this.removeDepthStriderFromSelf(wearerBoots, wearer);
		}
	}

	/**
	 * removes the enchantment DEPTH_STRIDER from the passed itemStack if applicable
	 *
	 * @param boots  an {@link ItemStack} that <i>MUST</i> be boots (be equipped to feet) and <i>MUST</i> be {@link WaterArmor}
	 * @param wearer the wearer of the boots; used to check if depthStrider is in affect
	 */
	private void removeDepthStriderFromSelf(ItemStack boots, @Nonnull EntityLivingBase wearer) {
		// do the boots have depth strider?
		if(EnchantmentHelper.getDepthStriderModifier(wearer) > 0) {
			// in case we need to remove depth strider
			NBTTagList bootEnchantments = boots.getEnchantmentTagList();
			// remove depth strider (getting the enchantment tag list ensures a new one is created if it doesn't exist already)
			int depthStriderIndex = 0;
			// get the index that depthStrider is located at
			for(int i = 0; i < bootEnchantments.tagCount(); i++) {
				NBTTagCompound currentCompound = bootEnchantments.getCompoundTagAt(i);
				if(DEPTH_STRIDER.equals(Enchantment.getEnchantmentByID(currentCompound.getShort("id")))) {
					// set our depthStriderIndex and break out
					depthStriderIndex = i;
					break;
				}
			}
			// remove the nbtTag located at depthStriderIndex
			bootEnchantments.removeTag(depthStriderIndex);
			// write to wearerBoots
			boots.setTagInfo("ench", bootEnchantments);
		}
	}
}
