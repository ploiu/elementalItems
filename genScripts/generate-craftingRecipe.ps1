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
                    return [PSCustomObject]@{item = $_.Contains(':') ? $_ : "minecraft:$_"};
                }
            }
        }

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
