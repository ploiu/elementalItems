$elements = 'plain', 'fire', 'ice', 'water', 'leaf', 'earth', 'air', 'ender';

./generate-allblockstateModels.ps1;

# for each element, generate a block loot table
$elements | ForEach-Object {
	./generate-blockLootTable.ps1 -blockName "block_crystal_$_";
	# generate ore drops
	./generate-blockLootTable.ps1 -blockName "ore_$_" -itemNames @("elementalitems:crystal_$_") -rolls 1;
}

# ice has a special loot table, it can drop between 2 and 4 ice crystals. It's also very different from all the other loot tables so Imma just copy glowstone and write directly
$iceOreLootTable = @"
{"type": "minecraft:block","pools": [{"rolls": 1,"entries": [{"type": "minecraft:alternatives","children": [{"type": "minecraft:item","conditions": [{"condition": "minecraft:match_tool","predicate": {"enchantments": [{"enchantment": "minecraft:silk_touch","levels": {"min": 1}}]}}],"name": "elementalitems:ore_ice"},{"type": "minecraft:item","functions": [{"function": "minecraft:set_count","count": {"min": 2.0,"max": 4.0,"type": "minecraft:uniform"}},{"function": "minecraft:apply_bonus","enchantment": "minecraft:fortune","formula": "minecraft:uniform_bonus_count","parameters": {"bonusMultiplier": 1}},{"function": "minecraft:limit_count","limit": {"max": 4,"min": 2}},{"function": "minecraft:explosion_decay"}],"name": "elementalitems:crystal_ice"}]}]}]}
"@

$iceOreLootTable | convertfrom-json | ConvertTo-Json -Depth 100 > ../../src/main/resources/data/elementalitems/loot_tables/blocks/ore_ice.json;

# for nether crystal ore
./generate-blockLootTable.ps1 -blockName "ore_fire_nether" -itemNames @("elementalitems:crystal_fire") -rolls 1;
