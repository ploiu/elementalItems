param(
	[string]$recipeName,
	[string]$crystalItem,
	[string]$outputItem
)

# keep track of the original directory
$origDir = $pwd;
$recipe = 'C', 'S', 'S';
$key = @{C = $crystalItem; S = 'stick' }
./generate-craftingRecipe.ps1 -recipeName $recipeName -recipe $recipe -keys $key -output $outputItem -count 4

# return to the original directory
Set-Location $origDir;
