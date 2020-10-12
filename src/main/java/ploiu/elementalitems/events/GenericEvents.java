package ploiu.elementalitems.events;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ploiu.elementalitems.helpers.LootTableHelper;
import ploiu.elementalitems.util.Utils;

@Mod.EventBusSubscriber(modid = "elementalitems")
@SuppressWarnings("unused")
public class GenericEvents {

	@SubscribeEvent
	public static void onSmeltingUpdated(RecipesUpdatedEvent event) {
		System.out.println(event);
		event.getRecipeManager().getRecipes()
				.parallelStream()
				.filter(recipe -> recipe.getType() == IRecipeType.SMELTING)
				.forEach(recipe -> {
					Ingredient ingredient = recipe.getIngredients().get(0);
					// add create a new entry for each input item
					for(ItemStack stack : ingredient.getMatchingStacks()) {
						Utils.smeltingRecipes.put(stack, recipe.getRecipeOutput());
					}
				});
	}

	@SubscribeEvent
	public static void onLoadLoot(LootTableLoadEvent event) {
		// this way may not be the most efficient, but it's definitely easily readable and easy to add to
		LootTableHelper.loadLootTableIfEventMatches(event, "chests/buried_treasure");
		LootTableHelper.loadLootTableIfEventMatches(event, "chests/desert_pyramid");
		LootTableHelper.loadLootTableIfEventMatches(event, "chests/end_city_treasure");
		LootTableHelper.loadLootTableIfEventMatches(event, "chests/igloo_chest");
		LootTableHelper.loadLootTableIfEventMatches(event, "chests/jungle_temple");
		LootTableHelper.loadLootTableIfEventMatches(event, "chests/nether_bridge");
		LootTableHelper.loadLootTableIfEventMatches(event, "chests/stronghold_corridor");
		LootTableHelper.loadLootTableIfEventMatches(event, "chests/stronghold_crossing");
		LootTableHelper.loadLootTableIfEventMatches(event, "chests/stronghold_library");
		LootTableHelper.loadLootTableIfEventMatches(event, "chests/village/village_armorer");
		LootTableHelper.loadLootTableIfEventMatches(event, "chests/village/village_toolsmith");
		LootTableHelper.loadLootTableIfEventMatches(event, "chests/village/village_weaponsmith");
	}
}
