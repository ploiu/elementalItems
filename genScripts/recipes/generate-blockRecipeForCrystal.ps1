param(
	[string]$element
)

# keep track of the original directory
$origDir = $pwd;
$crystalArray = [PSCustomObject[]]@();
1..9 | ForEach-Object {
	$crystalArray += "elementalitems:crystal_$element";
}
./generate-craftingRecipe.ps1 -recipeName "block_crystal_$element" -shapeless -output "elementalitems:block_crystal_$element" -count 1 -items $crystalArray;

# return to the original directory
Set-Location $origDir;
