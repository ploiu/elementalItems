param(
	[string]$recipeName,
	[string]$crystalItem,
	[string]$outputItem
)

# keep track of the original directory
$origDir = $pwd;
$recipe = 'C C', 'CCC', 'CCC';
$key = @{C = $crystalItem;}
./generate-craftingRecipe.ps1 -recipeName $recipeName -shaped -recipe $recipe -keys $key -output $outputItem

# return to the original directory
Set-Location $origDir;
