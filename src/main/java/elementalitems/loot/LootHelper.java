package elementalitems.loot;

import elementalitems.ElementalItems;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import org.apache.logging.log4j.Level;

import static elementalitems.ElementalItems.MOD_ID;
import static elementalitems.ElementalItems.logger;

public class LootHelper {

	private LootHelper() {
	}

	private LootPool crystalFirePool;
	private LootPool crystalIcePool;
	private LootPool crystalEnderPool;
	private LootPool crystalPlainPool;
	private LootPool crystalLeafPool;
	private LootPool swordLifeDeathPool;

	public LootPool getCrystalFirePool() {
		return this.crystalFirePool;
	}

	public LootPool getCrystalLeafPool() {
		return this.crystalLeafPool;
	}

	public LootPool getCrystalPlainPool() {
		return this.crystalPlainPool;
	}

	public LootPool getCrystalIcePool() {
		return this.crystalIcePool;
	}

	public LootPool getCrystalEnderPool() {
		return this.crystalEnderPool;
	}

	public LootPool getSwordLifeDeathPool() {
		return this.swordLifeDeathPool;
	}

	public static LootHelper getInstance() {
		return LootHelper.SingletonHelper.instance;
	}

	/**
	 * called during mod initialization; loads all the loot pools for our above-declared pools
	 */
	private void createLootPools() {
		logger.log(Level.INFO, "Loading loot pools...");

		LootEntry crystalFireEntry = new LootEntryTable(new ResourceLocation(ElementalItems.MOD_ID + ":inject/crystal_fire"), 5, 1, new LootCondition[0], "crystal_fire");
		this.crystalFirePool = new LootPool(new LootEntry[]{crystalFireEntry}, new LootCondition[0], new RandomValueRange(0, 2), new RandomValueRange(0, 0), "crystal_fire");

		LootEntry crystalIceEntry = new LootEntryTable(new ResourceLocation(ElementalItems.MOD_ID + ":inject/crystal_ice"), 5, 1, new LootCondition[0], "crystal_ice");
		this.crystalIcePool = new LootPool(new LootEntry[]{crystalIceEntry}, new LootCondition[0], new RandomValueRange(0, 2), new RandomValueRange(0, 0), "crystal_ice");

		LootEntry crystalEnderEntry = new LootEntryTable(new ResourceLocation(ElementalItems.MOD_ID + ":inject/crystal_ender"), 5, 1, new LootCondition[0], "crystal_ender");
		this.crystalEnderPool = new LootPool(new LootEntry[]{crystalEnderEntry}, new LootCondition[0], new RandomValueRange(0, 2), new RandomValueRange(0, 0), "crystal_ender");

		LootEntry crystalPlainEntry = new LootEntryTable(new ResourceLocation(ElementalItems.MOD_ID + ":inject/crystal_plain"), 5, 1, new LootCondition[0], "crystal_plain");
		this.crystalPlainPool = new LootPool(new LootEntry[]{crystalPlainEntry}, new LootCondition[0], new RandomValueRange(0, 2), new RandomValueRange(0, 0), "crystal_plain");

		LootEntry crystalLeafEntry = new LootEntryTable(new ResourceLocation(ElementalItems.MOD_ID + ":inject/crystal_leaf"), 5, 1, new LootCondition[0], "crystal_leaf");
		this.crystalLeafPool = new LootPool(new LootEntry[]{crystalLeafEntry}, new LootCondition[0], new RandomValueRange(0, 2), new RandomValueRange(0, 0), "crystal_leaf");

		LootEntry swordLifeDeathEntry = new LootEntryTable(new ResourceLocation(ElementalItems.MOD_ID + ":inject/sword_life_death"), 1, 1, new LootCondition[0], "sword_life_death");
		this.swordLifeDeathPool = new LootPool(new LootEntry[]{swordLifeDeathEntry}, new LootCondition[0], new RandomValueRange(0, 1), new RandomValueRange(0), "sword_life_death");

		logger.log(Level.INFO, "Finished loading loot pools");
	}

	public void registerLootTables() {
		logger.log(Level.INFO, "Registering loot tables...");
		this.register("inject/crystal_fire");
		this.register("inject/crystal_ice");
		this.register("inject/crystal_ender");
		this.register("inject/sword_life_death");
		logger.log(Level.INFO, "Finished registering loot tables");
		this.createLootPools();
	}

	private void register(String resourceName) {
		LootTableList.register(new ResourceLocation(MOD_ID, resourceName));
	}

	private static class SingletonHelper {
		private static final LootHelper instance = new LootHelper();
	}
}
