package ploiu.elementalitems.recipe;

import net.minecraft.data.CustomRecipeBuilder;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;

public class ElementalItemsRecipeRegistry {
	public static SpecialRecipeSerializer<RecipeRepairFlamethrower> repairFlamethrowerRecipe = register("elementalitems:crafting_flamethrower_repair", new SpecialRecipeSerializer<>(RecipeRepairFlamethrower::new));

	/**
	 * taken from {@link IRecipeSerializer#register}
	 */
	private static <S extends IRecipeSerializer<T>, T extends IRecipe<?>> S register(String key, S recipeSerializer) {
		return (S) (Registry.<IRecipeSerializer<?>>register(Registry.RECIPE_SERIALIZER, key, recipeSerializer));
	}

	public static void registerRecipes(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
		event.getRegistry().registerAll(
				repairFlamethrowerRecipe
		);
		System.out.println("debug");
	}
}
