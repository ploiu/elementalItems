package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.combat.ElementalEffects;

public class EarthSword extends BaseSword {

	public EarthSword() {
		super(ElementalTypes.EARTH);
	}

	@Override
	public void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
		if(!target.level.getBlockState(new BlockPos(target.getPosition(0f).subtract(new Vector3d(0, 1, 0)))).getMaterial().blocksMotion()) {
			ElementalEffects.strikeDown(target);
		} else {
			ElementalEffects.bury(target);
		}
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
