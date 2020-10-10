package ploiu.elementalitems;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.recipe.ElementalItemsRecipeRegistry;

@Mod("elementalitems")
public class ElementalItems {
	public static final Logger LOGGER = LogManager.getLogger();

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

		@SubscribeEvent
		public static void entityRegistry(final RegistryEvent.Register<EntityType<?>> event) {
			LOGGER.info("Registering entities...");
			ElementalItemsEntityRegistry.registerEntities(event);
		}

		@SubscribeEvent
		public static void recipeRegistry(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
			ElementalItemsRecipeRegistry.registerRecipes(event);
		}
	}
}
