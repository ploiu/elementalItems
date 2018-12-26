package elementalitems.sharedeffects.combat;

import elementalitems.util.EntityUtils;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;

public interface ISharedLeafEffect {
	default void enchantWithSmite(ItemStack stackToEnchant, Entity itemOwner) {
		if(EntityUtils.getInstance().isValidEntityLivingBase(itemOwner)) {
			// enchant it with smite V if not enchanted with Smite already
			if(EnchantmentHelper.getEnchantmentLevel(Enchantments.SMITE, stackToEnchant) <= 0) {
				stackToEnchant.addEnchantment(Enchantments.SMITE, 5);
			}
		}
	}
}
