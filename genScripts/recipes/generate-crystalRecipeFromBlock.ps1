param(
	[string]$element
)

# keep track of the original directory
$origDir = $pwd;
./generate-craftingRecipe.ps1 -recipeName "crystal_$($element)_from_block" -shapeless -output "elementalitems:crystal_$element" -count 9 -items @("elementalitems:block_crystal_$element");

# return to the original directory
Set-Location $origDir;
