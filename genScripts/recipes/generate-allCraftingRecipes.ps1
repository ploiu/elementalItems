$elements = 'plain', 'fire', 'ice', 'water', 'leaf', 'earth', 'air', 'ender';

# for each element, generate an axe, pickaxe, and shovel recipe
$elements | ForEach-Object {
	$type = $_;
	$crystalType = "elementalitems:crystal_$type";
	.\generate-axeRecipe.ps1 -recipeName "axe_$type" -bladeItem $crystalType -outputItem "elementalitems:axe_$type";
	.\generate-pickaxeRecipe.ps1 -recipeName "pickaxe_$type" -bladeItem $crystalType -outputItem "elementalitems:pickaxe_$type";
	.\generate-shovelRecipe.ps1 -recipeName "shovel_$type" -bladeItem $crystalType -outputItem "elementalitems:shovel_$type";
	.\generate-swordRecipe.ps1 -recipeName "sword_$type" -bladeItem $crystalType -outputItem "elementalitems:sword_$type";
	.\generate-armorRecipesForType.ps1 -type $type;
	.\generate-arrowRecipe.ps1 -recipeName "arrow_$type" -crystalItem $crystalType -outputItem "elementalitems:arrow_$type";
	# crystal from block
	.\generate-crystalRecipeFromBlock.ps1 -element $type;
	# block recipe from crystal
	.\generate-blockRecipeForCrystal.ps1 -element $type;
}
# generate the crystals
.\generate-craftingRecipe.ps1 -recipeName 'crystal_fire_smelting' -smelting -ingredient 'elementalitems:crystal_plain' -exp 1 -cookingTime 200 -output 'elementalitems:crystal_fire';
.\generate-crystalRecipe.ps1 -recipeName 'crystal_ice' -outputItem 'elementalitems:crystal_ice' -outterItem 'snowball';
.\generate-craftingRecipe.ps1 -recipeName 'crystal_water_smelting' -smelting -ingredient 'elementalitems:crystal_ice' -exp 1 -cookingTime 100 -output 'elementalitems:crystal_water';
<#START LEAF CRYSTAL#>
$leaves = @("acacia_leaves", "birch_leaves", "dark_oak_leaves", "jungle_leaves", "oak_leaves", "spruce_leaves");
.\generate-craftingRecipe -recipeName 'crystal_leaf' -shaped -recipe @('SSS', 'SCS', 'SSS') -keys @{S = $leaves; C = 'elementalitems:crystal_plain' } -output 'elementalitems:crystal_leaf';
<#END LEAF CRYSTAL#>
<#START EARTH CRYSTAL#>
$hardenedClayArray = @('black_terracotta', 'blue_terracotta', 'brown_terracotta', 'cyan_terracotta', 'gray_terracotta', 'green_terracotta', 'lime_terracotta', 'magenta_terracotta', 'orange_terracotta', 'pink_terracotta', 'purple_terracotta', 'red_terracotta', 'white_terracotta', 'yellow_terracotta', 'light_blue_terracotta', 'terracotta');
.\generate-craftingRecipe.ps1 -recipeName 'crystal_earth' -shaped -recipe @('SSS', 'SCS', 'SSS') -keys @{S = $hardenedClayArray; C = 'elementalitems:crystal_plain' } -output 'elementalitems:crystal_earth'
<#END EARTH CRYSTAL#>
.\generate-craftingRecipe.ps1 -recipeName 'crystal_air' -shaped -recipe @('PPP', 'PCP', 'GGG') -keys @{P = 'paper'; C = 'elementalitems:crystal_plain'; G = 'gunpowder' } -output 'elementalitems:crystal_air';
.\generate-crystalRecipe -recipeName 'crystal_ender' -outputItem 'elementalitems:crystal_ender' -outterItem 'ender_pearl';


<#START DUAL SWORDS#>
@(
	@('fire', 'earth'),
	@('fire', 'ice'),
	@('fire', 'water'),
	@('water', 'air'),
	@('ice', 'air'),
	@('ice', 'ender'),
	@('water', 'leaf'),
	@('ice', 'water'),
	@('leaf', 'air')
) | ForEach-Object {
	.\generate-dualSwordRecipe.ps1 -type1 $_[0] -type2 $_[1];
}
<#END DUAL SWORDS#>
# flamethrower
.\generate-craftingRecipe.ps1 -recipeName 'flamethrower' -shaped -recipe @('OOO', 'OOF', 'O  ') -key @{O = 'obsidian'; F = 'elementalitems:block_crystal_fire' } -output 'elementalitems:flamethrower';
# armor crusher
.\generate-craftingRecipe.ps1 -recipeName 'armor_crusher' -shaped -recipe @(' EX', ' OO', 'O  ') -key @{E = 'elementalitems:block_crystal_earth'; X = 'elementalitems:crystal_earth'; O = 'obsidian' } -output 'elementalitems:warhammer';
