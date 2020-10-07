package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.combat.ElementalEffects;

public class WaterSword extends BaseSword {

	public WaterSword() {
		super(ElementalTypes.WATER);
	}

	@Override
	public void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
		// no-op
	}

	@Override
	protected void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		ElementalEffects.enchantWithKnockback(stack);
	}

	@Override
	public void onUsed(World world, PlayerEntity player, Hand hand) {
		// no-op
	}
}
