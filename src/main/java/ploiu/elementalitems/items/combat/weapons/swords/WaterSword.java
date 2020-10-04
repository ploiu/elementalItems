package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.util.ItemUtils;

public class WaterSword extends BaseSword {

	public WaterSword() {
		super(ElementalTypes.WATER);
	}

	@Override
	public void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
	}

	@Override
	public void onUsed(World world, PlayerEntity player, Hand hand) {

	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity inventoryOwner, int itemSlot, boolean isSelected) {
		// remove the knockback enchantment from the stack to prevent duplicate enchantments
		ItemUtils.removeEnchantmentFromItem(stack, "knockback");
		stack.addEnchantment(Enchantments.KNOCKBACK, 5);
	}
}
