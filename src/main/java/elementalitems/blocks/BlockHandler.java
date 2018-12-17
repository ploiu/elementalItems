package elementalitems.blocks;

import elementalitems.ElementalItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Block handler.
 */
@SuppressWarnings("unused")
public class BlockHandler {
	/**
	 * The constant blocks.
	 */
	//the list of BaseBlocks to register
	public static List<BaseBlock> blocks = new ArrayList<>();

	/**
	 * The constant plainCrystalOre.
	 */
	// ore blocks
	public static BaseOre plainCrystalOre = new PlainCrystalOre();
	/**
	 * The constant fireCrystalOre.
	 */
	public static BaseOre fireCrystalOre = new FireCrystalOre();
	/**
	 * The constant waterCrystalOre.
	 */
	public static BaseOre waterCrystalOre = new WaterCrystalOre();
	/**
	 * The constant leafCrystalOre.
	 */
	public static BaseOre leafCrystalOre = new LeafCrystalOre();
	/**
	 * The constant iceCrystalOre.
	 */
	public static BaseOre iceCrystalOre = new IceCrystalOre();
	/**
	 * The constant earthCrystalOre.
	 */
	public static BaseOre earthCrystalOre = new EarthCrystalOre();
	/**
	 * The constant airCrystalOre.
	 */
	public static BaseOre airCrystalOre = new AirCrystalOre();
	/**
	 * The constant endCrystalOre.
	 */
	public static BaseBlock endCrystalOre = new BaseBlock("ore_end");

	/**
	 * The constant plainCrystalBlock.
	 */
	// crystal blocks
	public static BaseBlock plainCrystalBlock = new BaseBlock("block_crystal_plain", true);
	/**
	 * The constant fireCrystalBlock.
	 */
	public static BaseBlock fireCrystalBlock = new BaseBlock("block_crystal_fire", true);
	/**
	 * The constant waterCrystalBlock.
	 */
	public static BaseBlock waterCrystalBlock = new BaseBlock("block_crystal_water", true);
	/**
	 * The constant leafCrystalBlock.
	 */
	public static BaseBlock leafCrystalBlock = new BaseBlock("block_crystal_leaf", true);
	/**
	 * The constant iceCrystalBlock.
	 */
	public static BaseBlock iceCrystalBlock = new BaseBlock("block_crystal_ice", true, Material.PACKED_ICE);
	/**
	 * The constant earthCrystalBlock.
	 */
	public static BaseBlock earthCrystalBlock = new BaseBlock("block_crystal_earth", true);
	/**
	 * The constant airCrystalBlock.
	 */
	public static BaseBlock airCrystalBlock = new BaseBlock("block_crystal_air", true);
	/**
	 * The constant enderCrystalBlock.
	 */
	public static BaseBlock enderCrystalBlock = new BaseBlock("block_crystal_ender", true);

	/**
	 * Register.
	 *
	 * @param registry the registry
	 */
	public static void register(IForgeRegistry<Block> registry) {
		ElementalItems.logger.log(Level.INFO, "Registering blocks...");
		//register all of our blocks
		registry.registerAll(blocks.toArray(new BaseBlock[]{}));
	}

	/**
	 * Register item blocks.
	 *
	 * @param registry the registry
	 */
	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		ElementalItems.logger.log(Level.INFO, "Registering block items...");
		//register all of our itemBlocks
		blocks.forEach((BaseBlock block) -> registry.register(block.createBlockItem(block)));
	}

	/**
	 * Register models.
	 */
	public static void registerModels() {
		ElementalItems.logger.log(Level.INFO, "Registering block models...");
		blocks.forEach((BaseBlock baseBlock) -> ElementalItems.proxy.registerItemRenderer(Item.getItemFromBlock(baseBlock), 0));
	}
}
