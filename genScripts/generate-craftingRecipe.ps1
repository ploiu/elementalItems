<#
    .SYNOPSIS
    creates the json for a crafting recipe and stores it in the correct location
#>

param(
    [Parameter(Mandatory = $true)]
    [string]$recipeName,
    [Parameter(Mandatory = $true)]
    [string[]]$recipe,
    [Parameter(Mandatory = $true)]
    [hashtable]$keys,
    [Parameter(Mandatory = $true)]
    [string]$output,
    [string]$type = 'crafting_shaped',
    [int]$count = 1
)

# since we will move directories, get the current one to go back to
$origDir = $pwd;

function convertKeys {
    $converted = @{};
    # for each entry in our keys, convert it to the proper format (from char=value)
    $keys.GetEnumerator() | ForEach-Object {
        $converted.($_.Key) = @{
            item = $_.Value.Contains(':') ? $_.Value : "minecraft:$($_.Value)";
        };
    }
    return $converted;
}

$contents = [PSCustomObject]@{
    type    = "minecraft:$type";
    pattern = $recipe;
    key     = convertKeys;
    result  = @{
        item  = $output.Contains(':') ? $output : "minecraft:$output";
        count = $count;
    }
}

# git should be replaced with your directory where the project root folder is in
Set-Location $git/elementalitems/src/main/resources/data/elementalitems/recipes;

($contents | ConvertTo-Json -Depth 100 )> "$recipeName.json";

Set-Location $origDir;
