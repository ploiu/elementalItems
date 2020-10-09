param (
	[string]$worldName = 'ElementalItemsTesting'
)

Compress-Archive -Path ../run/saves/$worldName/datapacks/elementalitems -DestinationPath ../datapackForTesting.zip -CompressionLevel 'fastest' -Force;
