<#
    .SYNOPSIS
    creates the json for a crafting recipe and stores it in the correct location
#>

param(
    [Parameter(Mandatory = $true)]
    [string]$recipeName,

    [Parameter(ParameterSetName = "shaped")]
    [switch]$shaped,
    [Parameter(ParameterSetName = "shaped")]
    [string[]]$recipe,
    [Parameter(ParameterSetName = "shaped")]
    [hashtable]$keys,

    [Parameter(ParameterSetName = "shapeless")]
    [switch]$shapeless,
    [Parameter(ParameterSetName = "shapeless")]
    [string[]]$items,

    [Parameter(ParameterSetName = "smelting")]
    [switch]$smelting,
    [Parameter(ParameterSetName = "smelting")]
    [string]$ingredient,
    [Parameter(ParameterSetName = "smelting")]
    [double]$exp,
    [Parameter(ParameterSetName = "smelting")]
    [int]$cookingTime = 200,

    [Parameter(Mandatory = $true)]
    [string]$output,
    [int]$count = 1
)

# since we will move directories, get the current one to go back to
$origDir = $pwd;

function convertKeysForShaped {
    $converted = @{};
    # for each entry in our keys, convert it to the proper format (from char=value)
    $keys.GetEnumerator() | ForEach-Object {
        $entry = $_;
        if ($entry.Value.GetType().BaseType -ne [System.Array]) {
            $converted.($entry.Key) = @{
                item = $entry.Value.Contains(':') ? $entry.Value : "minecraft:$($_.Value)";
            };
        }
        else {
            $converted.($entry.Key) = $entry.Value | ForEach-Object {
                if ($_.GetType() -eq [hashtable]) {
                    return [PSCustomObject]$_;
                }
                else {
                    return [PSCustomObject]@{item = $_.Contains(':') ? $_ : "minecraft:$_" };
                }
            }
        }

    }
    return $converted;
}

function convertKeysForShapeless {
    $contents = @();
    $items | ForEach-Object {
        $contents += @{
            item = $_.contains(':') ? $_ : "minecraft:$_";
        }
    }
    return $contents;
}

$contents = $null;

if ($shaped.IsPresent) {
    $contents = [PSCustomObject]@{
        type    = "minecraft:crafting_shaped";
        pattern = $recipe;
        key     = convertKeysForShaped;
        result  = @{
            item  = $output.Contains(':') ? $output : "minecraft:$output";
            count = $count;
        }
    }
}
elseif ($shapeless.IsPresent) {
    $contents = [PSCustomObject]@{
        type        = "minecraft:crafting_shapeless";
        ingredients = convertKeysForShapeless;
        result      = @{
            item  = $output.Contains(':') ? $output : "minecraft:$output";
            count = $count;
        }
    };
}
elseif ($smelting.IsPresent) {
    $contents = [PSCustomObject]@{
        type        = "minecraft:smelting";
        ingredient  = @{
            item = $ingredient;
        };
        result      = $output.contains(':') ? $output : "minecraft:$output"
        experience  = $exp;
        cookingTime = $cookingTime;
        count       = $count
    }
}

# git should be replaced with your directory where the project root folder is in
Set-Location $git/elementalitems/src/main/resources/data/elementalitems/recipes;
($contents | ConvertTo-Json -Depth 100 )> "$recipeName.json";

Set-Location $origDir;
