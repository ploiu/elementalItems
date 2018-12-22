package elementalitems;

import elementalitems.items.ItemHandler;
import elementalitems.items.combat.armor.BaseArmor;
import elementalitems.items.combat.armor.WaterArmor;
import elementalitems.loot.LootHelper;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber
@SuppressWarnings("unused")
public class ElementalItemsEventHandler {

	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event) {
		// check the event's target to ensure that it's a real thing
		EntityLivingBase hurtEntity = event.getEntityLiving();
		if(EntityUtils.getInstance().isValidEntityLivingBase(hurtEntity) && event.getSource() != null) {
			// get the hurt entity's armor list and check each piece
			Iterable<ItemStack> hurtEntityArmorInventory = hurtEntity.getArmorInventoryList();
			hurtEntityArmorInventory.forEach(itemStack -> {
				// get the item from the itemStack
				Item stackItem = itemStack.getItem();
				// check if it's an instance of BaseArmor
				if(stackItem instanceof BaseArmor) {
					// apply the effect
					((BaseArmor) stackItem).onUserHurt(event.getSource(), hurtEntity);
				}
			});
		}
	}

	@SubscribeEvent
	public static void onExplosion(ExplosionEvent.Detonate event) {
		// get the list of entities affected by the event
		event.getAffectedEntities().removeIf(entity -> {
			if(EntityUtils.getInstance().isValidEntityLivingBase(entity)) {
				List<ItemStack> entityArmor = new ArrayList<>();
				// it would be much more simple if this were a Collection instead of an Iterable, but NOOOO we HAVE to do it this way...
				entity.getArmorInventoryList().forEach(entityArmor::add);
				return entityArmor.removeIf(stack -> stack != null && stack.getItem() instanceof WaterArmor);
			}
			return false;
		});
	}

	@SubscribeEvent
	public static void onKnockBack(LivingKnockBackEvent event) {
		EntityLivingBase hurtEntity = event.getEntityLiving();
		if(EntityUtils.getInstance().isValidEntityLivingBase(hurtEntity) && EntityUtils.getInstance().doesEntityHaveFullElementalSetOfType(hurtEntity, ElementalType.EARTH)) {
			// cancel the event for the entity
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
		if(EntityUtils.getInstance().isValidEntityLivingBase(event.getEntityLiving()) && event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer eventEntity = (EntityPlayer) event.getEntityLiving();
			if(EntityUtils.getInstance().doesEntityHaveFullElementalSetOfType(eventEntity, ElementalType.AIR) && !eventEntity.isCreative() && !eventEntity.isSpectator()) {
				eventEntity.capabilities.allowFlying = true;
				//				eventEntity.capabilities.isFlying = true;
				eventEntity.fallDistance = 0.0f;
			} else if(!eventEntity.isCreative() && !eventEntity.isSpectator()) {
				// prevent them from flying
				eventEntity.capabilities.allowFlying = false;
				eventEntity.capabilities.isFlying = false;
			}
		}
	}

	@SubscribeEvent
	public static void onEntityFall(LivingFallEvent event) {
		EntityLivingBase eventLiving = event.getEntityLiving();
		if(EntityUtils.getInstance().isValidEntityLivingBase(eventLiving)) {
			// get the entity's boots and check if they're air armor
			if(ItemHandler.airBoots.equals(eventLiving.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem())) {
				event.setDamageMultiplier(0);
			}
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void onWaterFogRender(EntityViewRenderEvent.FogDensity event) {
		Entity eventEntity = event.getEntity();
		// validate the entity
		if(EntityUtils.getInstance().isValidEntityLivingBase(eventEntity) && eventEntity.isInWater()) {
			EntityLivingBase livingBase = (EntityLivingBase) event.getEntity();
			ItemStack helmet = livingBase.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
			if(ItemHandler.waterHelmet.equals(helmet.getItem())) {
				event.setCanceled(true);
				// cancel the event and set the fog to be really low
				event.setDensity(0f);
			}
		}
	}

	@SubscribeEvent
	public static void onLivingTeleport(EnderTeleportEvent event) {
		// prevent if the event's entity is wearing a full set of ender armor
		if(EntityUtils.getInstance().doesEntityHaveFullElementalSetOfType(event.getEntity(), ElementalType.ENDER)) {
			// set the event's damage to 0
			event.setAttackDamage(0f);
		}
	}

	@SubscribeEvent
	public static void loadLoot(LootTableLoadEvent event) {
		// add fire crystals to nether & desert chests
		LootTable table = event.getTable();
		if(event.getName() != null && event.getName().toString().split("chests/").length == 2) {
			switch(event.getName().toString().split("chests/")[1]) {
				case "desert_pyramid":
				case "nether_bridge":
					table.addPool(LootHelper.getInstance().getCrystalFirePool());
					break;
				case "igloo_chest":
					table.addPool(LootHelper.getInstance().getCrystalIcePool());
					break;
				case "jungle_temple":
				case "woodland_mansion":
					table.addPool(LootHelper.getInstance().getCrystalLeafPool());
					break;
				case "village_blacksmith":
				case "simple_dungeon":
				case "abandoned_mineshaft":
					table.addPool(LootHelper.getInstance().getCrystalPlainPool());
					table.addPool(LootHelper.getInstance().getCrystalFirePool());
					break;
				case "end_city_treasure":
					table.addPool(LootHelper.getInstance().getCrystalEnderPool());
					break;
				case "stronghold_corridor":
				case "stronghold_crossing":
				case "stronghold_library":
					table.addPool(LootHelper.getInstance().getSwordLifeDeathPool());
				default:
					break;
			}
		}
	}
}
