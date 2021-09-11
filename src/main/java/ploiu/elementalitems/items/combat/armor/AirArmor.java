package ploiu.elementalitems.items.combat.armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.util.EntityUtils;

public class AirArmor extends BaseArmorItem {
	public AirArmor(EquipmentSlotType slot) {
		super(ElementalTypes.AIR, slot);
	}

	@Override
	public void onUserHurt(ItemStack stack, World world, DamageSource source, LivingEntity wearer) {
		// get the damage source entity and validate it
		if(EntityUtils.isValidLivingEntity(source.getDirectEntity())) {
			LivingEntity attacker = (LivingEntity) source.getDirectEntity();
			// knockback the attacker based on the number of pieces being worn and the attacker's knockback resistance
			float effectMultiplier = EntityUtils.getNumberOfElementalArmorForType(ElementalTypes.AIR, wearer) / 4f;
			// dividing the sound volume so that 4 pieces isn't ear splitting but also so that 1 piece isn't super quiet
			world.playSound(null, wearer.getX(), wearer.getY(), wearer.getZ(), SoundEvents.BAT_TAKEOFF, SoundCategory.NEUTRAL, 0.5f / EntityUtils.getNumberOfElementalArmorForType(ElementalTypes.AIR, wearer), 1f);
			attacker.knockback(effectMultiplier, -MathHelper.sin(attacker.yRot * 0.017453292F), MathHelper.cos(attacker.yRot * 0.017453292F));
			// spawn particles to signify the attacker flying back
			if(world instanceof ServerWorld) {
				((ServerWorld)world).sendParticles(ParticleTypes.CLOUD, attacker.getRandomX(1.0D), attacker.getRandomY(), attacker.getRandomZ(1.0D), 0, 0, 0, 0, .1);
			}
		}
	}

	@Override
	public void applyPassiveEffect(ItemStack stack, World world, LivingEntity wearer) {
		// TODO
	}
}
