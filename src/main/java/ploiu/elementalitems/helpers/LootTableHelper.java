package ploiu.elementalitems.helpers;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;

public class LootTableHelper {
	/**
	 * loads our loot table entries into Minecraft's one if the passed loot table matches the event's current loot table
	 *
	 * @param event
	 * @param lootTable
	 */
	public static void loadLootTableIfEventMatches(LootTableLoadEvent event, String lootTable) {
		// get the minecraft version of the loot table
		final String MCLootTable = "minecraft:" + lootTable;
		if(event.getName().toString().equalsIgnoreCase(MCLootTable)) {
			event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation("elementalitems", lootTable))).build());
		}
	}
}
