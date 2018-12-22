package elementalitems.blocks;

import elementalitems.ElementalItems;
import elementalitems.blocks.ore.*;
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

	//the list of BaseBlocks to register
	protected static final List<BaseBlock> blocks = new ArrayList<>();


	// ore blocks
	public static BaseOre plainCrystalOre;

	public static BaseOre fireCrystalOre;

	public static BaseOre waterCrystalOre;

	public static BaseOre leafCrystalOre;

	public static BaseOre iceCrystalOre;

	public static BaseOre earthCrystalOre;

	public static BaseOre airCrystalOre;

	public static BaseOre endCrystalOre;


	// crystal blocks
	public static BaseBlock plainCrystalBlock;

	public static BaseBlock fireCrystalBlock;

	public static BaseBlock waterCrystalBlock;

	public static BaseBlock leafCrystalBlock;

	public static BaseBlock iceCrystalBlock;

	public static BaseBlock earthCrystalBlock;

	public static BaseBlock airCrystalBlock;

	public static BaseBlock enderCrystalBlock;

	public static void initializeBlocks() {
		// ores
		plainCrystalOre = new PlainCrystalOre();
		fireCrystalOre = new FireCrystalOre();
		waterCrystalOre = new WaterCrystalOre();
		leafCrystalOre = new LeafCrystalOre();
		iceCrystalOre = new IceCrystalOre();
		earthCrystalOre = new EarthCrystalOre();
		airCrystalOre = new AirCrystalOre();
		endCrystalOre = new EndCrystalOre();
		// crystal blocks
		plainCrystalBlock = new BaseBlock("block_crystal_plain", true);
		fireCrystalBlock = new BaseBlock("block_crystal_fire", true);
		fireCrystalBlock.setLightLevel(1F);
		waterCrystalBlock = new BaseBlock("block_crystal_water", true);
		waterCrystalBlock.setResistance(2000F);
		leafCrystalBlock = new BaseBlock("block_crystal_leaf", true);
		iceCrystalBlock = new BaseBlock("block_crystal_ice", true, Material.PACKED_ICE);
		iceCrystalBlock.setDefaultSlipperiness(0.98F);
		earthCrystalBlock = new BaseBlock("block_crystal_earth", true);
		airCrystalBlock = new BaseBlock("block_crystal_air", true);
		enderCrystalBlock = new BaseBlock("block_crystal_ender", true);
	}

	/**
	 * Register.
	 *
	 * @param registry the registry
	 */
	public static void register(IForgeRegistry<Block> registry) {
		initializeBlocks();
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
