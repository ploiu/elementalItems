$elements = 'plain', 'fire', 'ice', 'water', 'leaf', 'earth', 'air', 'ender';

$elements | ForEach-Object {
	.\generate-blockstateModel.ps1 -blockName "block_crystal_$_";
	.\generate-blockstateModel.ps1 -blockName "ore_$_";
}

# generate for nether fire ore
.\generate-blockstateModel.ps1 -blockName "ore_fire_nether";
