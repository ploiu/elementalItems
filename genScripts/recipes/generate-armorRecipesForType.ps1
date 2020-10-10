param(
	[string]$type
)

.\generate-helmetRecipe.ps1 -recipeName "head_$type" -crystalItem "elementalitems:crystal_$type" -outputItem "elementalitems:head_$type";
.\generate-chestplateRecipe.ps1 -recipeName "chest_$type" -crystalItem "elementalitems:crystal_$type" -outputItem "elementalitems:chest_$type";
.\generate-leggingsRecipe.ps1 -recipeName "legs_$type" -crystalItem "elementalitems:crystal_$type" -outputItem "elementalitems:legs_$type";
.\generate-bootsRecipe.ps1 -recipeName "feet_$type" -crystalItem "elementalitems:crystal_$type" -outputItem "elementalitems:feet_$type";
