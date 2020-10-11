package ploiu.elementalitems.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import ploiu.elementalitems.ElementalItems;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.blocks.cystalblocks.CrystalBlock;
import ploiu.elementalitems.blocks.cystalblocks.FireCrystalBlock;
import ploiu.elementalitems.blocks.cystalblocks.IceCrystalBlock;
import ploiu.elementalitems.blocks.ores.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ElementalItemsBlockRegistry {
	public static final List<BaseBlock> blocks = new ArrayList<>();
	public static final List<BlockItem> blockItems = new ArrayList<>();

	// crystal blocks
	public static final CrystalBlock plainCrystalBlock = new CrystalBlock(ElementalTypes.PLAIN);
	public static final CrystalBlock fireCrystalBlock = new FireCrystalBlock();
	public static final CrystalBlock iceCrystalBlock = new IceCrystalBlock();
	public static final CrystalBlock waterCrystalBlock = new CrystalBlock(ElementalTypes.WATER);
	public static final CrystalBlock leafCrystalBlock = new CrystalBlock(ElementalTypes.LEAF);
	public static final CrystalBlock earthCrystalBlock = new CrystalBlock(ElementalTypes.EARTH);
	public static final CrystalBlock airCrystalBlock = new CrystalBlock(ElementalTypes.AIR);
	public static final CrystalBlock enderCrystalBlock = new CrystalBlock(ElementalTypes.ENDER);

	// ores
	public static final BaseOre plainCrystalOre = new PlainCrystalOre();
	public static final BaseOre fireCrystalOre = new FireCrystalOre();
	public static final BaseOre iceCrystalOre = new IceCrystalOre();
	public static final BaseOre waterCrystalOre = new WaterCrystalOre();
	public static final BaseOre leafCrystalOre = new LeafCrystalOre();
	public static final BaseOre earthCrystalOre = new EarthCrystalOre();
	public static final BaseOre airCrystalOre = new AirCrystalOre();
	public static final BaseOre enderCrystalOre = new EndCrystalOre();
	public static final BaseOre netherCrystalOre = new NetherCrystalOre();

	// item versions of the crystal blocks
	public static final BlockItem plainCrystalBlockItem = createBlockItemForBlock(plainCrystalBlock);
	public static final BlockItem fireCrystalBlockItem = createBlockItemForBlock(fireCrystalBlock);
	public static final BlockItem iceCrystalBlockItem = createBlockItemForBlock(iceCrystalBlock);
	public static final BlockItem waterCrystalBlockItem = createBlockItemForBlock(waterCrystalBlock);
	public static final BlockItem leafCrystalBlockItem = createBlockItemForBlock(leafCrystalBlock);
	public static final BlockItem earthCrystalBlockItem = createBlockItemForBlock(earthCrystalBlock);
	public static final BlockItem airCrystalBlockItem = createBlockItemForBlock(airCrystalBlock);
	public static final BlockItem enderCrystalBlockItem = createBlockItemForBlock(enderCrystalBlock);

	// item versions of the ore blocks
	public static final BlockItem plainCrystalOreItem = createBlockItemForBlock(plainCrystalOre);
	public static final BlockItem fireCrystalOreItem = createBlockItemForBlock(fireCrystalOre);
	public static final BlockItem iceCrystalOreItem = createBlockItemForBlock(iceCrystalOre);
	public static final BlockItem waterCrystalOreItem = createBlockItemForBlock(waterCrystalOre);
	public static final BlockItem leafCrystalOreItem = createBlockItemForBlock(leafCrystalOre);
	public static final BlockItem earthCrystalOreItem = createBlockItemForBlock(earthCrystalOre);
	public static final BlockItem airCrystalOreItem = createBlockItemForBlock(airCrystalOre);
	public static final BlockItem enderCrystalOreItem = createBlockItemForBlock(enderCrystalOre);
	public static final BlockItem netherCrystalOreItem = createBlockItemForBlock(netherCrystalOre);


	public static void addBlock(BaseBlock block) {
		blocks.add(block);
	}

	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		blocks.forEach(event.getRegistry()::register);
	}

	/**
	 * creates a blockItem for the passed block and adds it to our blockItems list to be registered later
	 *
	 * @param block
	 * @return
	 */
	private static BlockItem createBlockItemForBlock(BaseBlock block) {
		BlockItem item = (BlockItem) new BlockItem(block, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName());
		blockItems.add(item);
		return item;
	}

	public static void registerItemBlocks(final RegistryEvent.Register<Item> registry) {
		ElementalItems.LOGGER.info("Registering block items...");
		//register all of our itemBlocks
		blockItems.forEach(registry.getRegistry()::register);
	}
}
