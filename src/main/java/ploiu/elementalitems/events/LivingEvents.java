package ploiu.elementalitems.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
				((BaseArmorItem) stack.getItem()).onUserHurt(stack, target.getEntityWorld(), event.getSource(), target);
			}
		}
	}
}
