package elementalitems.sharedeffects.combat;

import elementalitems.util.EntityUtils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;

public interface ISharedWaterCombatEffect {

	default void enchantWithKnockBack(ItemStack stackToEnchant, Entity stackOwner) {
		// make sure that our entity that's getting the enchanted item is valid first
		if(EntityUtils.getInstance().isValidEntityLivingBase(stackOwner)) {
			// if it's not enchanted yet we must enchant it with knockback V
			if(EnchantmentHelper.getEnchantmentLevel(Enchantments.KNOCKBACK, stackToEnchant) <= 0) {
				// TODO based on player level
				stackToEnchant.addEnchantment(Enchantments.KNOCKBACK, 5);
			}
		}
	}
}
