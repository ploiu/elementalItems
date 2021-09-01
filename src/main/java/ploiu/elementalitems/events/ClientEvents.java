package ploiu.elementalitems.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.util.EntityUtils;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = "elementalitems")
@SuppressWarnings("unused")
public class ClientEvents {

	@SubscribeEvent
	public static void onLavaFog(EntityViewRenderEvent.FogDensity event) {
		Entity eventEntity = event.getInfo().getEntity();
		if(EntityUtils.isValidLivingEntity(eventEntity)) {
			// if the entity is in lava and has the lava helmet
			if(EntityUtils.doesEntityHaveArmorPieceEquipped(eventEntity, ElementalItemsItemRegistry.fireHelmet) && eventEntity.isInLava()) {
				if(event.isCancelable()) {
					event.setCanceled(true);
					event.setDensity(0.0f);
				}
			} else if(EntityUtils.doesEntityHaveArmorPieceEquipped(eventEntity, ElementalItemsItemRegistry.waterHelmet) && eventEntity.isInWater()) {
				if(event.isCancelable()) {
					event.setCanceled(true);
					event.setDensity(0.0f);
				}
			}
		}
	}
}
