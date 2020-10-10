param(
	[string]$recipeName,
	[string]$crystalItem,
	[string]$outputItem
)

# keep track of the original directory
$origDir = $pwd;
$recipe = 'C', 'S', 'F';
$key = @{C = $crystalItem; S = 'stick'; F = 'feather'}
./generate-craftingRecipe.ps1 -recipeName $recipeName -shaped -recipe $recipe -keys $key -output $outputItem -count 4

# return to the original directory
Set-Location $origDir;
