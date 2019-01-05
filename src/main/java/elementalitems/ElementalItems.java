package elementalitems;

import elementalitems.blocks.BlockHandler;
import elementalitems.entities.EntityHandler;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import elementalitems.loot.LootHelper;
import elementalitems.proxy.CommonProxy;
import elementalitems.worldgen.WorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

@Mod(modid = ElementalItems.MOD_ID, name = ElementalItems.NAME, version = ElementalItems.VERSION, updateJSON = "https://raw.githubusercontent.com/ploiu/elementalItems/master/update.json")
@SuppressWarnings("unused")
public class ElementalItems {
	public static final String MOD_ID = "elementalitems";
	public static final String NAME = "Elemental Items Mod";
	public static final String VERSION = "1.3";
	//the mod instance;
	@Mod.Instance(MOD_ID)
	public static ElementalItems instance;
	//the proxy for this mod;
	@SidedProxy(serverSide = "elementalitems.proxy.CommonProxy", clientSide = "elementalitems.proxy.ClientProxy")
	public static CommonProxy proxy;

	// the logger for this mod
	public static Logger logger;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		logger.log(Level.INFO, "Initializing materials...");
		ElementalMaterials.getInstance().registerMaterials();
		EntityHandler.registerRenderers();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		logger.log(Level.INFO, "Registering world generator...");
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 50);
		EntityHandler.register();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// register loot tables
		LootHelper.getInstance().registerLootTables();
		logger.log(Level.INFO, "Finished loading!");
	}

	@Mod.EventBusSubscriber
	private static class RegistrationHandler {

		/**
		 * registers the items for our mod;
		 *
		 * @param event the event that gets passed in by forge or something
		 */
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			//register our normal items
			ItemHandler.register(event.getRegistry());
			//register the item blocks
			BlockHandler.registerItemBlocks(event.getRegistry());
		}

		/**
		 * Registers the models for our items;
		 *
		 * @param event;
		 */
		@SubscribeEvent
		public static void registerModels(ModelRegistryEvent event) {
			//item models;
			ItemHandler.registerModels();
			//block models;
			BlockHandler.registerModels();
		}

		/**
		 * Registers the blocks for our mod;
		 *
		 * @param event;
		 */
		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			BlockHandler.register(event.getRegistry());
		}
	}
}
