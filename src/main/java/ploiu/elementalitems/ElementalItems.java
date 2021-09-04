package ploiu.elementalitems;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ploiu.elementalitems.blocks.ElementalItemsBlockRegistry;
import ploiu.elementalitems.client.render.entity.ElementalItemsRenderingRegistry;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.recipe.ElementalItemsRecipeRegistry;
import ploiu.elementalitems.worldgen.EndGenerator;
import ploiu.elementalitems.worldgen.NetherGenerator;
import ploiu.elementalitems.worldgen.OverworldGenerator;


@Mod("elementalitems")
@SuppressWarnings("unused")
public class ElementalItems {
	public static final Logger LOGGER = LogManager.getLogger();

	public ElementalItems() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doCommonSetup);
	}

	private void doClientSetup(final FMLClientSetupEvent event) {
		// register the elemental arrow renderer with our arrow entities
		ElementalItemsRenderingRegistry.registerRenderers();
	}

	private void doCommonSetup(final FMLCommonSetupEvent event) {
		// TODO OverworldGenerator.setupOverworldGeneration();
		NetherGenerator.setupNetherGenerator();
		EndGenerator.setupEndGeneration();
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

		@SubscribeEvent
		public static void worldGenFeatureRegistry(final RegistryEvent.Register<Feature<?>> event) {
			// I don't want to do this, but I have to...
			// ElementalItemsFeatureRegistry.registerFeatures(event);
		}
	}
}
