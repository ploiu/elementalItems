param(
	[string]$recipeName,
	[string]$crystalItem,
	[string]$outputItem
)

# keep track of the original directory
$origDir = $pwd;
$recipe = 'CCC', 'CHC';
$key = @{C = $crystalItem; H = 'elementalitems:head_plain' }
./generate-craftingRecipe.ps1 -recipeName $recipeName -recipe $recipe -keys $key -output $outputItem

# return to the original directory
Set-Location $origDir;
