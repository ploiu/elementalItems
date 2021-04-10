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

/**
 * TODO allow flight and arrow immunity
 */
public class AirArmor extends BaseArmorItem {
	public AirArmor(EquipmentSlotType slot) {
		super(ElementalTypes.AIR, slot);
	}

	@Override
	public void onUserHurt(ItemStack stack, World world, DamageSource source, LivingEntity wearer) {
		// get the damage source entity and validate it
		if(EntityUtils.isValidLivingEntity(source.getImmediateSource())) {
			LivingEntity attacker = (LivingEntity) source.getImmediateSource();
			// knockback the attacker based on the number of pieces being worn and the attacker's knockback resistance
			float effectMultiplier = EntityUtils.getNumberOfElementalArmorForType(ElementalTypes.AIR, wearer) / 4f;
			// knockback the attacker
			if(!world.isRemote()) {
				world.playSound(null, wearer.getPosX(), wearer.getPosY(), wearer.getPosZ(), SoundEvents.ENTITY_BAT_TAKEOFF, SoundCategory.NEUTRAL, 0.5f, 1f);
			}
			attacker.func_233627_a_(effectMultiplier, -MathHelper.sin(attacker.rotationYaw * 0.017453292F), MathHelper.cos(attacker.rotationYaw * 0.017453292F));
			// spawn particles to signify the attacker flying back
			if(world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, attacker.getPosX(), attacker.getPosY(), attacker.getPosZ(), 10, attacker.getWidth(), attacker.getHeight(), attacker.getWidth(), 0.0);
			}
		}
	}

	@Override
	public void applyPassiveEffect(ItemStack stack, World world, LivingEntity wearer) {
		// TODO
	}
}
