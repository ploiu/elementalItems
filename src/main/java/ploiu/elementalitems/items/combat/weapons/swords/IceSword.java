package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class IceSword extends BaseSword {

	public IceSword() {
		super(ElementalTypes.ICE);
	}

	@Override
	public void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
		// apply slow and weakness to the target
		target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1));
		target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200, 1));
	}

	@Override
	public void onUsed(World world, PlayerEntity player, Hand hand) {
		world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		if(!world.isRemote) {
			SnowballEntity snowballentity = new SnowballEntity(world, player);
			snowballentity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 3F, 1.0F);
			world.addEntity(snowballentity);
		}
	}
}
