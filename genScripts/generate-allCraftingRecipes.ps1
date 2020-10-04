$elements = 'fire', 'ice', 'water', 'leaf', 'earth', 'air', 'ender';

# create the plain item crafting recipes first
.\generate-craftingRecipe.ps1 -recipeName pickaxe_plain -recipe @('BBB', ' H ', ' H ') -keys @{B = 'elementalitems:crystal_plain'; H = 'stick' } -output 'elementalitems:pickaxe_plain';
.\generate-craftingRecipe.ps1 -recipeName axe_plain -recipe @('BB', 'HB', 'H') -keys @{B = 'elementalitems:crystal_plain'; H = 'stick' } -output 'elementalitems:axe_plain';
.\generate-craftingRecipe.ps1 -recipeName shovel_plain -recipe @('B', 'H', 'H') -keys @{B = 'elementalitems:crystal_plain'; H = 'stick' } -output 'elementalitems:shovel_plain';

# for each element, generate an axe, pickaxe, and shovel recipe
$elements | ForEach-Object {
	$type = $_;
	$crystalType = "elementalitems:crystal_$type";
	.\generate-axeRecipe.ps1 -recipeName "axe_$type" -bladeItem $crystalType -outputItem "elementalitems:axe_$type";
	.\generate-pickaxeRecipe.ps1 -recipeName "pickaxe_$type" -bladeItem $crystalType -outputItem "elementalitems:pickaxe_$type";
	.\generate-shovelRecipe.ps1 -recipeName "shovel_$type" -bladeItem $crystalType -outputItem "elementalitems:shovel_$type";
	# TODO arrows, swords, armor
}

# TODO special items
