param(
	[string]$type1,
	[string]$type2
)

$origDir = $pwd;

.\generate-craftingRecipe.ps1 -shapeless -recipeName "sword_$($type1)_$($type2)" -output "elementalitems:sword_$($type1)_$($type2)" -items @("elementalitems:sword_$type1", "elementalitems:sword_$type2");

Set-Location $origDir;
