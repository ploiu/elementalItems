package ploiu.elementalitems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;

@Mod("elementalitems")
public class ElementalItems {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Class used to register blocks and items and stuff
	 */
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void blockRegistry(final RegistryEvent.Register<Block> event) {
			LOGGER.info("Registering blocks...");
		}

		@SubscribeEvent
		public static void itemRegistry(final RegistryEvent.Register<Item> event) {
			LOGGER.info("Registering items...");
			ElementalItemsItemRegistry.registerItems(event);
		}
	}
}
