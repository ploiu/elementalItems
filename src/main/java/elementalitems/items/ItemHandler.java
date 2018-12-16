package elementalitems.items;

import elementalitems.ElementalItems;
import elementalitems.items.combat.armor.*;
import elementalitems.items.combat.arrows.BaseArrow;
import elementalitems.items.combat.swords.*;
import elementalitems.items.tools.axes.*;
import elementalitems.items.tools.pickaxes.*;
import elementalitems.items.tools.shovels.*;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

import static elementalitems.ElementalType.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;

/**
 * The type Item handler.
 */
@SuppressWarnings("unused")
public class ItemHandler {
	//arrayList of base items to register
	public static List<ElementalItem> items = new ArrayList<>();
	/**
	 * The constant plainCrystal.
	 */
	// the crystals
	public static ElementalItem plainCrystal = new BaseCrystal(PLAIN);
	/**
	 * The constant fireCrystal.
	 */
	public static ElementalItem fireCrystal = new BaseCrystal(FIRE);
	public static ElementalItem waterCrystal = new BaseCrystal(WATER);
	public static ElementalItem leafCrystal = new BaseCrystal(LEAF);
	public static ElementalItem iceCrystal = new BaseCrystal(ICE);
	public static ElementalItem earthCrystal = new BaseCrystal(EARTH);
	public static ElementalItem airCrystal = new BaseCrystal(AIR);
	public static ElementalItem enderCrystal = new BaseCrystal(ENDER);

	// swords
	public static ElementalItem fireSword = new FireSword();
	public static ElementalItem iceSword = new IceSword();
	public static ElementalItem waterSword = new WaterSword();
	public static ElementalItem leafSword = new LeafSword();
	public static ElementalItem airSword = new AirSword();
	public static ElementalItem earthSword = new EarthSword();
	public static ElementalItem enderSword = new EnderSword();
	public static ElementalItem lifeDeathSword = new LifeDeathSword();
	public static ElementalItem plainSword = new PlainSword();

	// pickaxes
	public static ElementalItem firePickaxe = new FirePickaxe();
	public static ElementalItem icePickaxe = new IcePickaxe();
	public static ElementalItem waterPickaxe = new WaterPickaxe();
	public static ElementalItem leafPickaxe = new LeafPickaxe();
	public static ElementalItem airPickaxe = new AirPickaxe();
	public static ElementalItem earthPickaxe = new EarthPickaxe();
	public static ElementalItem enderPickaxe = new EnderPickaxe();
	public static ElementalItem plainPickaxe = new PlainPickaxe();

	// axes
	public static ElementalItem fireAxe = new FireAxe();
	public static ElementalItem iceAxe = new IceAxe();
	public static ElementalItem waterAxe = new WaterAxe();
	public static ElementalItem leafAxe = new LeafAxe();
	public static ElementalItem airAxe = new AirAxe();
	public static ElementalItem earthAxe = new EarthAxe();
	public static ElementalItem enderAxe = new EnderAxe();
	public static ElementalItem plainAxe = new PlainAxe();

	// shovels
	public static ElementalItem fireShovel = new FireShovel();
	public static ElementalItem iceShovel = new IceShovel();
	public static ElementalItem waterShovel = new WaterShovel();
	public static ElementalItem leafShovel = new LeafShovel();
	public static ElementalItem airShovel = new AirShovel();
	public static ElementalItem earthShovel = new EarthShovel();
	public static ElementalItem enderShovel = new EnderShovel();
	public static ElementalItem plainShovel = new PlainShovel();

	// armor
	public static ElementalItem fireHelmet = new FireArmor(HEAD);
	public static ElementalItem fireChestplate = new FireArmor(CHEST);
	public static ElementalItem fireLeggings = new FireArmor(LEGS);
	public static ElementalItem fireBoots = new FireArmor(FEET);

	public static ElementalItem iceHelmet = new IceArmor(HEAD);
	public static ElementalItem iceChestplate = new IceArmor(CHEST);
	public static ElementalItem iceLeggings = new IceArmor(LEGS);
	public static ElementalItem iceBoots = new IceArmor(FEET);

	public static ElementalItem waterHelmet = new WaterArmor(HEAD);
	public static ElementalItem waterChestplate = new WaterArmor(CHEST);
	public static ElementalItem waterLeggings = new WaterArmor(LEGS);
	public static ElementalItem waterBoots = new WaterArmor(FEET);

	public static ElementalItem leafHelmet = new LeafArmor(HEAD);
	public static ElementalItem leafChestplate = new LeafArmor(CHEST);
	public static ElementalItem leafLeggings = new LeafArmor(LEGS);
	public static ElementalItem leafBoots = new LeafArmor(FEET);

	public static ElementalItem earthHelmet = new EarthArmor(HEAD);
	public static ElementalItem earthChestplate = new EarthArmor(CHEST);
	public static ElementalItem earthLeggings = new EarthArmor(LEGS);
	public static ElementalItem earthBoots = new EarthArmor(FEET);

	public static ElementalItem airHelmet = new AirArmor(HEAD);
	public static ElementalItem airChestplate = new AirArmor(CHEST);
	public static ElementalItem airLeggings = new AirArmor(LEGS);
	public static ElementalItem airBoots = new AirArmor(FEET);

	public static ElementalItem enderHelmet = new EnderArmor(HEAD);
	public static ElementalItem enderChestplate = new EnderArmor(CHEST);
	public static ElementalItem enderLeggings = new EnderArmor(LEGS);
	public static ElementalItem enderBoots = new EnderArmor(FEET);

	public static ElementalItem plainHelmet = new PlainArmor(HEAD);
	public static ElementalItem plainChestplate = new PlainArmor(CHEST);
	public static ElementalItem plainLeggings = new PlainArmor(LEGS);
	public static ElementalItem plainBoots = new PlainArmor(FEET);

	// arrows
	public static ElementalItem fireArrow = new BaseArrow(FIRE);
	public static ElementalItem iceArrow = new BaseArrow(ICE);
	public static ElementalItem waterArrow = new BaseArrow(WATER);
	public static ElementalItem leafArrow = new BaseArrow(LEAF);
	public static ElementalItem airArrow = new BaseArrow(AIR);
	public static ElementalItem earthArrow = new BaseArrow(EARTH);
	public static ElementalItem enderArrow = new BaseArrow(ENDER);
	public static ElementalItem plainArrow = new BaseArrow(PLAIN);

	private ItemHandler() {
	}


	/**
	 * Registers all of our items
	 *
	 * @param registry the forge registry we are registering everything with
	 */
	public static void register(IForgeRegistry<Item> registry) {
		ElementalItems.logger.log(Level.INFO, "Registering items...");
		//register all of our items with the registry
		registry.registerAll(items.toArray(new Item[]{}));
	}

	/**
	 * Registers all of our item's models
	 */
	public static void registerModels() {
		ElementalItems.logger.log(Level.INFO, "Registering item models...");
		items.forEach((ElementalItem item) -> ElementalItems.proxy.registerItemRenderer((Item) item, 0));
	}
}
