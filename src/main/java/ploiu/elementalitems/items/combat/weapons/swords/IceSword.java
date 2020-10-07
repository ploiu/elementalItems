package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.combat.ElementalEffects;

public class IceSword extends BaseSword {

	public IceSword() {
		super(ElementalTypes.ICE);
	}

	@Override
	public void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
		ElementalEffects.slowAndWeakenTarget(target);
	}

	@Override
	protected void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		// no-op
	}

	@Override
	public void onUsed(World world, PlayerEntity player, Hand hand) {
		ElementalEffects.throwSnowball(world, player);
	}
}
