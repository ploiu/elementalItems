package ploiu.elementalitems;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ploiu.elementalitems.blocks.ElementalItemsBlockRegistry;
import ploiu.elementalitems.client.render.entity.BaseEntityArrowRenderer;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;
import ploiu.elementalitems.entity.arrow.*;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.recipe.ElementalItemsRecipeRegistry;

@Mod("elementalitems")
@SuppressWarnings("unused")
public class ElementalItems {
	public static final Logger LOGGER = LogManager.getLogger();

	public ElementalItems() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientSetup);
	}

	public void doClientSetup(final FMLClientSetupEvent event) {
		// register the elemental arrow renderer with our arrow entities
		@SuppressWarnings("unchecked")
		Class<BaseEntityArrow>[] elementalArrows = new Class[]{
				EntityPlainArrow.class,
				EntityFireArrow.class,
				EntityIceArrow.class,
				EntityWaterArrow.class,
				EntityLeafArrow.class,
				EntityEarthArrow.class,
				EntityAirArrow.class,
				EntityEnderArrow.class
		};
		for(Class<BaseEntityArrow> elementalArrow : elementalArrows) {
			RenderingRegistry.registerEntityRenderingHandler(elementalArrow, BaseEntityArrowRenderer::new);
		}
	}

	/**
	 * Class used to register blocks and items and stuff
	 */
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void blockRegistry(final RegistryEvent.Register<Block> event) {
			LOGGER.info("Registering blocks...");
			ElementalItemsBlockRegistry.registerBlocks(event);
		}

		@SubscribeEvent
		public static void itemRegistry(final RegistryEvent.Register<Item> event) {
			LOGGER.info("Registering items...");
			ElementalItemsItemRegistry.registerItems(event);
			ElementalItemsBlockRegistry.registerItemBlocks(event);
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
