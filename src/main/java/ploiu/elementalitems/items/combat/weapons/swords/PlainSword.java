package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class PlainSword extends BaseSword {

	public PlainSword() {
		super(ElementalTypes.PLAIN);
	}

	@Override
	public void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
	}

	@Override
	public void onUsed(World world, PlayerEntity player, Hand hand) {
		
	}
}
