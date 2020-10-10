$elements = 'plain', 'fire', 'ice', 'water', 'leaf', 'earth', 'air', 'ender';

./generate-allblockstateModels.ps1;

# for each element, generate a block loot table
$elements | ForEach-Object {
	./generate-blockLootTable.ps1 -blockName "block_crystal_$_";
}
