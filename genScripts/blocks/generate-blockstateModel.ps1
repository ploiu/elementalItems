param(
	[string]$blockName
)

# keep track of the current directory as we will change directories
$origDir = $pwd;
$blockstate = @{
	variants = @{
		"" = @{
			model = "elementalitems:block/$blockName"
		}
	}
};

$blockModel = @{
	parent = "block/cube_all";
	textures = @{
		all = "elementalitems:blocks/$blockName"
	}
};

$blockInventory = @{
	parent = "elementalitems:block/$blockName";
}

# write the 3 objects to the correct files
$blockstate | ConvertTo-Json -Depth 100 > ../../src/main/resources/assets/elementalitems/blockstates/$blockName.json
$blockModel | ConvertTo-Json -Depth 100 > ../../src/main/resources/assets/elementalitems/models/block/$blockName.json
$blockInventory | ConvertTo-Json -Depth 100 > ../../src/main/resources/assets/elementalitems/models/item/$blockName.json

cd $origDir;
