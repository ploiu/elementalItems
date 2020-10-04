param(
	[string]$recipeName,
	[string]$outterItem,
	[string]$outputItem
)

# keep track of the original directory
$origDir = $pwd;
$recipe = 'BBB', 'BHB', 'BBB';
$key = @{B = $outterItem; H = 'elementalitems:crystal_plain' }
./generate-craftingRecipe.ps1 -recipeName $recipeName -recipe $recipe -keys $key -output $outputItem

# return to the original directory
Set-Location $origDir;
