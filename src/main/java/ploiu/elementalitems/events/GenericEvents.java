package ploiu.elementalitems.events;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.client.event.RecipesUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
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
}
