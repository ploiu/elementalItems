param(
	[string]$blockName,
	[int]$rolls = 1,
	[string[]]$itemNames = @("elementalitems:$blockName")
)

function createItemEntry {
	$items = @();
	$itemNames | ForEach-Object {
		$items += @{
			type = 'minecraft:item';
			name = $_.contains(':') ? $_ : "minecraft:$_";
		}
	}
	return [Object[]]$items;
}

$obj = @{
	type  = 'minecraft:block';
	pools = @(
		@{
			rolls      = $rolls;
			entries    = [Object[]](createItemEntry);
			conditions = @(
				@{
					condition = 'minecraft:survives_explosion'
				}
			)
		}
	)
}

# now write obj to the correct file
$obj | ConvertTo-Json -Depth 100 > ../../src/main/resources/data/elementalitems/loot_tables/blocks/$blockName.json
