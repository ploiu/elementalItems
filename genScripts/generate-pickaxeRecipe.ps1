param(
	[string]$recipeName,
	[string]$bladeItem,
	[string]$outputItem,
	[string]$handleItem = 'elementalitems:pickaxe_plain'
)

# keep track of the original directory
$origDir = $pwd;
$recipe = 'BBB', ' H ';
$key = @{B = $bladeItem; H = $handleItem }
./generate-craftingRecipe.ps1 -recipeName $recipeName -recipe $recipe -keys $key -output $outputItem

# return to the original directory
Set-Location $origDir;
