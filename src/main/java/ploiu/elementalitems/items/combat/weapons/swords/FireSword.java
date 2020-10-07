package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class FireSword extends BaseSword {

	public FireSword() {
		super(ElementalTypes.FIRE);
	}

	@Override
	public void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
		target.setFire(10);
	}

	@Override
	protected void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		// no-op
	}

	@Override
	public void onUsed(World world, PlayerEntity player, Hand hand) {
		// no-op
	}
}
