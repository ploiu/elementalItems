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
}
# generate the crystals
.\generate-crystalRecipe.ps1 -recipeName 'crystal_ice' -outputItem 'elementalitems:crystal_ice' -outterItem 'snowball';
.\generate-craftingRecipe.ps1 -recipeName 'crystal_air' -recipe @('PPP', 'PCP', 'GGG') -keys @{P = 'paper'; C = 'elementalitems:crystal_plain'; G = 'gunpowder' } -output 'elementalitems:crystal_air';
<#START EARTH CRYSTAL#>
$hardenedClayArray = @('black_terracotta', 'blue_terracotta', 'brown_terracotta', 'cyan_terracotta', 'gray_terracotta', 'green_terracotta', 'lime_terracotta', 'magenta_terracotta', 'orange_terracotta', 'pink_terracotta', 'purple_terracotta', 'red_terracotta', 'white_terracotta', 'yellow_terracotta', 'light_blue_terracotta', 'terracotta');
.\generate-craftingRecipe.ps1 -recipeName 'crystal_earth' -recipe @('SSS', 'SCS', 'SSS') -keys @{S = $hardenedClayArray; C = 'elementalitems:crystal_plain' } -output 'elementalitems:crystal_earth'
<#END EARTH CRYSTAL#>
# TODO special items
