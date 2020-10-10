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

	// item versions of the crystal blocks
	public static final BlockItem plainCrystalBlockItem = createBlockItemForBlock(plainCrystalBlock);
	public static final BlockItem fireCrystalBlockItem = createBlockItemForBlock(fireCrystalBlock);
	public static final BlockItem iceCrystalBlockItem = createBlockItemForBlock(iceCrystalBlock);
	public static final BlockItem waterCrystalBlockItem = createBlockItemForBlock(waterCrystalBlock);
	public static final BlockItem leafCrystalBlockItem = createBlockItemForBlock(leafCrystalBlock);
	public static final BlockItem earthCrystalBlockItem = createBlockItemForBlock(earthCrystalBlock);
	public static final BlockItem airCrystalBlockItem = createBlockItemForBlock(airCrystalBlock);
	public static final BlockItem enderCrystalBlockItem = createBlockItemForBlock(enderCrystalBlock);


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
