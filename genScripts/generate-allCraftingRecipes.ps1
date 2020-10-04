$elements = 'fire', 'ice', 'water', 'leaf', 'earth', 'air', 'ender';

# create the plain item crafting recipes first
.\generate-craftingRecipe.ps1 -recipeName pickaxe_plain -recipe @('BBB', ' H ', ' H ') -keys @{B = 'elementalitems:crystal_plain'; H = 'stick' } -output 'elementalitems:pickaxe_plain';
.\generate-craftingRecipe.ps1 -recipeName axe_plain -recipe @('BB', 'HB', 'H ') -keys @{B = 'elementalitems:crystal_plain'; H = 'stick' } -output 'elementalitems:axe_plain';
.\generate-craftingRecipe.ps1 -recipeName shovel_plain -recipe @('B', 'H', 'H') -keys @{B = 'elementalitems:crystal_plain'; H = 'stick' } -output 'elementalitems:shovel_plain';
.\generate-craftingRecipe.ps1 -recipeName sword_plain -recipe @('B', 'B', 'H') -keys @{B = 'elementalitems:crystal_plain'; H = 'stick' } -output 'elementalitems:sword_plain';
# armor pieces
.\generate-craftingRecipe.ps1 -recipeName head_plain -recipe @('CCC', 'C C') -keys @{C = 'elementalitems:crystal_plain' } -output 'elementalitems:head_plain';
.\generate-craftingRecipe.ps1 -recipeName chest_plain -recipe @('C C', 'CCC', 'CCC') -keys @{C = 'elementalitems:crystal_plain' } -output 'elementalitems:chest_plain';
.\generate-craftingRecipe.ps1 -recipeName legs_plain -recipe @('CCC', 'C C', 'C C') -keys @{C = 'elementalitems:crystal_plain' } -output 'elementalitems:legs_plain';
.\generate-craftingRecipe.ps1 -recipeName feet_plain -recipe @('C C', 'C C') -keys @{C = 'elementalitems:crystal_plain' } -output 'elementalitems:feet_plain';
# generic arrow
.\generate-arrowRecipe.ps1 -recipeName "arrow_plain" -crystalItem 'elementalitems:crystal_plain' -outputItem "elementalitems:arrow_plain";
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

# TODO special items
