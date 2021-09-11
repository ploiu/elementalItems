package ploiu.elementalitems.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.core.jmx.Server;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.items.combat.armor.BaseArmorItem;
import ploiu.elementalitems.util.EntityUtils;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = "elementalitems")
public class LivingEvents {

	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event) {
		if(EntityUtils.isValidLivingEntity(event.getEntityLiving())) {
			LivingEntity target = event.getEntityLiving();
			List<ItemStack> targetArmor = EntityUtils.getEntityArmor(target);
			List<ItemStack> targetElementalArmor = targetArmor.stream()
					                                       .filter(stack -> stack.getItem() instanceof BaseArmorItem)
					                                       .collect(Collectors.toList());
			// apply the hurt effect for each piece of elemental armor
			for(ItemStack stack : targetElementalArmor) {
				((BaseArmorItem) stack.getItem()).onUserHurt(stack, target.level, event.getSource(), target);
			}
		}
	}

	@SubscribeEvent
	public static void onArrowImpact(ProjectileImpactEvent.Arrow event) {
		if(event.getRayTraceResult().getType() == RayTraceResult.Type.ENTITY) {
			if(EntityUtils.isValidLivingEntity(((EntityRayTraceResult) event.getRayTraceResult()).getEntity()) && event.getArrow() != null) {
				LivingEntity target = (LivingEntity) ((EntityRayTraceResult) event.getRayTraceResult()).getEntity();
				// if the target is wearing a full set of air armor, cancel the event and knock back the arrow
				if(EntityUtils.doesEntityHaveFullElementalSetOfType(ElementalTypes.AIR, target)) {
					// prevent the arrow from dealing damage
					event.setCanceled(true);
					AbstractArrowEntity arrow = event.getArrow();
					// allow the player to pick up the arrow once it lands
					arrow.setOwner(target);
					// stop the arrow and make it fall
					arrow.setDeltaMovement(0, -.1f, 0);
					World world = target.level;
					// play a sound and spawn particles to give sensory cues as to the arrow getting sent back to the attacker
					if(!world.isClientSide()) {
						world.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.BAT_TAKEOFF, SoundCategory.NEUTRAL, 0.5f, 1f);
					}
					if(world instanceof ServerWorld) {
						((ServerWorld)world).sendParticles(ParticleTypes.CLOUD, arrow.getRandomX(1.0D), arrow.getRandomY(), arrow.getRandomZ(1.0D), 0, 0, 0, 0, .1);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onExplosion(ExplosionEvent.Detonate event) {
		// get all entities in the affected entities that have water armor
		List<Entity> waterArmorEntities = event.getAffectedEntities().stream().filter(entity -> EntityUtils.getNumberOfElementalArmorForType(ElementalTypes.WATER, entity) > 0).collect(Collectors.toList());
		// now remove those entities from the affected entities list
		event.getAffectedEntities().removeIf(waterArmorEntities::contains);
	}

	@SubscribeEvent
	public static void onKnockback(LivingKnockBackEvent event) {
		if(EntityUtils.isValidLivingEntity(event.getEntityLiving()) && EntityUtils.doesEntityHaveFullElementalSetOfType(ElementalTypes.EARTH, event.getEntityLiving())) {
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
		if(EntityUtils.isValidLivingEntity(event.getEntityLiving()) && event.getEntityLiving() != null && event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity eventEntity = (PlayerEntity) event.getEntityLiving();
			if(EntityUtils.doesEntityHaveFullElementalSetOfType(ElementalTypes.AIR, eventEntity) && !eventEntity.isCreative() && !eventEntity.isSpectator()) {
				eventEntity.abilities.mayfly = true;
				// this is what allows them to begin flying
				eventEntity.fallDistance = 0.0f;
			} else if(!eventEntity.isCreative() && !eventEntity.isSpectator()) {
				// prevent them from flying
				eventEntity.abilities.mayfly = false;
				eventEntity.abilities.flying = false;
			}
		}
	}

	@SubscribeEvent
	public static void onEntityFall(LivingFallEvent event) {
		LivingEntity eventLiving = event.getEntityLiving();
		if(EntityUtils.isValidLivingEntity(eventLiving)) {
			// get the entity's boots and check if they're air armor
			if(ElementalItemsItemRegistry.airBoots.equals(eventLiving.getItemBySlot(EquipmentSlotType.FEET).getItem())) {
				event.setDamageMultiplier(0);
			}
		}
	}

	@SubscribeEvent
	public static void onLivingTeleport(EntityTeleportEvent.EnderPearl event) {
		// if the event's entity is wearing a full set of ender armor, prevent the teleport damage
		if(EntityUtils.doesEntityHaveFullElementalSetOfType(ElementalTypes.ENDER, event.getEntity())) {
			event.setAttackDamage(0);
		}
	}
}
