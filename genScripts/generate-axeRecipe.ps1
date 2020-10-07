param(
	[string]$recipeName,
	[string]$bladeItem,
	[string]$outputItem
)

# keep track of the original directory
$origDir = $pwd;
$recipe = 'BB', 'HB', 'H ';
$key = @{B = $bladeItem; H = 'stick' }
./generate-craftingRecipe.ps1 -recipeName $recipeName -shaped -recipe $recipe -keys $key -output $outputItem

# return to the original directory
Set-Location $origDir;
