package elementalitems;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ElementalItems.MOD_ID)
public class ElementalItemsConfig {

	@Config.Comment("Whether or not particle effects with the swords will show")
	public static boolean showSwordParticles = true;

	@Config.Comment({
			"Whether or not to use the unsafe version of checking if an entity is valid to interact with",
			"Setting this to true will not check if that entity is still alive, and will attempt to interact with it anyway.",
			"This can have some benefits, such as swords and armor applying their effects all the time, not just while the entity is still alive (e.g. the Fire Sword will still set mobs on fire if the hit would kill it).",
			"However, this may be unsafe, and if your game crashes after setting this true, it's probably best to revert it to false."
	})
	@Config.Name("UseUnSafeEntityCheck")
	public static boolean shouldUseLessSafeCheckToValidateEntities = false;
	
	@Config.Name("ShouldFlamethrowerSetBlocksOnFire")
	@Config.Comment("Sometimes you just don't want to set your house on fire. Requires a world restart.")
	@Config.RequiresWorldRestart
	public static boolean shouldFlamethrowerSetBlocksOnFire = false;
	
	@Config.Name("ShouldFlamethrowerMakeSoundsWhenUsing")
	public static boolean shouldFlamethrowerMakeSound = true;
	
	@Config.RangeInt(min = 1, max = 500)
	@Config.Comment("Controls how much durability a fire crystal refills when refilling the flamethrower. Requires the world to be restarted.")
	@Config.Name("FlamethrowerRefuelAmountPerFireCrystal")
	@Config.RequiresWorldRestart
	public static int flamethrowerRepairAmountPerFireCrystal = 20;

	// the class that makes sure the config props are updated properly
	@Mod.EventBusSubscriber(modid = ElementalItems.MOD_ID)
	private static class ConfigEventHandler {

		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			// make sure the config that was changed was for our mod
			if(event.getModID().equals(ElementalItems.MOD_ID)) {
				// sync our config stuff
				ConfigManager.sync(ElementalItems.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}
