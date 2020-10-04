package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class AirSword extends BaseSword {

	public AirSword() {
		super(ElementalTypes.AIR);
	}

	@Override
	public void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
	}

	@Override
	public void onUsed(World world, PlayerEntity player, Hand hand) {
		
	}
}
