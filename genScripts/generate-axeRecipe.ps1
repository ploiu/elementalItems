param(
	[string]$recipeName,
	[string]$bladeItem,
	[string]$outputItem,
	[string]$handleItem = 'elementalitems:axe_plain'
)

# keep track of the original directory
$origDir = $pwd;
$recipe = ' BB', ' HB';
$key = @{B = $bladeItem; H = $handleItem }
./generate-craftingRecipe.ps1 -recipeName $recipeName -recipe $recipe -keys $key -output $outputItem

# return to the original directory
Set-Location $origDir;
