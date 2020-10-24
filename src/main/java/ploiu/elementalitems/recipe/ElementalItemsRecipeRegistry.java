package ploiu.elementalitems.recipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;

public class ElementalItemsRecipeRegistry {
	public static SpecialRecipeSerializer<RecipeRepairFlamethrower> repairFlamethrowerRecipe = (SpecialRecipeSerializer<RecipeRepairFlamethrower>) new SpecialRecipeSerializer<>(RecipeRepairFlamethrower::new).setRegistryName("elementalitems:crafting_flamethrower_repair");

	public static void registerRecipes(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
		event.getRegistry().registerAll(
				repairFlamethrowerRecipe
		);
	}
}
