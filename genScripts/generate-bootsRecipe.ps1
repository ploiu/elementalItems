param(
	[string]$recipeName,
	[string]$crystalItem,
	[string]$outputItem
)

# keep track of the original directory
$origDir = $pwd;
$recipe = 'C C', 'CHC';
$key = @{C = $crystalItem; H = 'elementalitems:feet_plain' }
./generate-craftingRecipe.ps1 -recipeName $recipeName -recipe $recipe -keys $key -output $outputItem

# return to the original directory
Set-Location $origDir;
