package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class EarthSword extends BaseSword {

	public EarthSword() {
		super(ElementalTypes.EARTH);
	}

	@Override
	public void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
		if(!target.getEntityWorld().getBlockState(target.getPosition().down(1)).isSolid()) {
			strikeDown(target);
		} else {
			bury(target);
		}
	}

	/**
	 * Sends the target out of the sky until they hit the ground, where they take double fall damage
	 *
	 * @param target the entity to strike out of the sky
	 */
	private void strikeDown(LivingEntity target) {
		// get the world the entity is in
		World world = target.getEntityWorld();
		// there doesn't need to be a body for this
		BlockPos pos = target.getPosition();
		int distance;
		for(int i = 0; ; i++) {
			// get the current block
			if(world.getBlockState(pos.down(i + 1)).isSolid()) {
				// set our distance and break
				distance = i;
				break;
			}
		}
		target.setBoundingBox(target.getBoundingBox().offset(0, -distance, 0));
		target.resetPositionToBB();
		target.fall(distance, 2);
	}
	
	private void bury(LivingEntity target) {
		float height = target.getHeight() + 1;
		target.setBoundingBox(target.getBoundingBox().offset(0, -height, 0));
		target.resetPositionToBB();
		final World world = target.getEntityWorld();
		if(!world.isRemote()) {
			// play a sound effect
			world.playSound(null, target.getPosition(), SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.NEUTRAL, 0.5f, 1f);
		}
	}

	@Override
	public void onUsed(World world, PlayerEntity player, Hand hand) {

	}
}
