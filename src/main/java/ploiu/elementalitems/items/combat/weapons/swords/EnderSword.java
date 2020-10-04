package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class EnderSword extends BaseSword {

	public EnderSword() {
		super(ElementalTypes.ENDER);
	}

	@Override
	public void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
		// teleport the target to a random location
		int min = -10;
		int max = 10;
		int newX = target.getRNG().nextInt(max - min) + min + target.getPosition().getX();
		int newZ = target.getRNG().nextInt(max - min) + min + target.getPosition().getZ();
		target.attemptTeleport(newX, target.posY, newZ, true);
		target.getEntityWorld().playSound(null, target.prevPosX, target.prevPosY, target.prevPosZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, target.getSoundCategory(), 1.0F, 1.0F);
		target.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
	}

	@Override
	public void onUsed(World world, PlayerEntity player, Hand hand) {
		if(!world.isRemote) {
			EnderPearlEntity enderpearl = new EnderPearlEntity(world, player);
			enderpearl.shoot(player, player.rotationPitch, player.rotationYaw, 0f, 1.5f, 1.0f);
			world.playSound(null, player.getPosition(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
			world.addEntity(enderpearl);
		}
	}
}
