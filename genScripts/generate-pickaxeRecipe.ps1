param(
	[string]$recipeName,
	[string]$bladeItem,
	[string]$outputItem
)

# keep track of the original directory
$origDir = $pwd;
$recipe = 'BBB', ' H ', ' H ';
$key = @{B = $bladeItem; H = 'stick' }
./generate-craftingRecipe.ps1 -recipeName $recipeName -recipe $recipe -keys $key -output $outputItem

# return to the original directory
Set-Location $origDir;
