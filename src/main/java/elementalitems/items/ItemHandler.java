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
	public static final List<ElementalItem> items = new ArrayList<>();
	/**
	 * The constant plainCrystal.
	 */
	// the crystals
	public static final ElementalItem plainCrystal = new BaseCrystal(PLAIN);
	/**
	 * The constant fireCrystal.
	 */
	public static final ElementalItem fireCrystal = new BaseCrystal(FIRE);
	public static final ElementalItem waterCrystal = new BaseCrystal(WATER);
	public static final ElementalItem leafCrystal = new BaseCrystal(LEAF);
	public static final ElementalItem iceCrystal = new BaseCrystal(ICE);
	public static final ElementalItem earthCrystal = new BaseCrystal(EARTH);
	public static final ElementalItem airCrystal = new BaseCrystal(AIR);
	public static final ElementalItem enderCrystal = new BaseCrystal(ENDER);

	// swords
	public static final ElementalItem fireSword = new FireSword();
	public static final ElementalItem iceSword = new IceSword();
	public static final ElementalItem waterSword = new WaterSword();
	public static final ElementalItem leafSword = new LeafSword();
	public static final ElementalItem airSword = new AirSword();
	public static final ElementalItem earthSword = new EarthSword();
	public static final ElementalItem enderSword = new EnderSword();
	public static final ElementalItem lifeDeathSword = new LifeDeathSword();
	public static final ElementalItem plainSword = new PlainSword();

	// pickaxes
	public static final ElementalItem firePickaxe = new FirePickaxe();
	public static final ElementalItem icePickaxe = new IcePickaxe();
	public static final ElementalItem waterPickaxe = new WaterPickaxe();
	public static final ElementalItem leafPickaxe = new LeafPickaxe();
	public static final ElementalItem airPickaxe = new AirPickaxe();
	public static final ElementalItem earthPickaxe = new EarthPickaxe();
	public static final ElementalItem enderPickaxe = new EnderPickaxe();
	public static final ElementalItem plainPickaxe = new PlainPickaxe();

	// axes
	public static final ElementalItem fireAxe = new FireAxe();
	public static final ElementalItem iceAxe = new IceAxe();
	public static final ElementalItem waterAxe = new WaterAxe();
	public static final ElementalItem leafAxe = new LeafAxe();
	public static final ElementalItem airAxe = new AirAxe();
	public static final ElementalItem earthAxe = new EarthAxe();
	public static final ElementalItem enderAxe = new EnderAxe();
	public static final ElementalItem plainAxe = new PlainAxe();

	// shovels
	public static final ElementalItem fireShovel = new FireShovel();
	public static final ElementalItem iceShovel = new IceShovel();
	public static final ElementalItem waterShovel = new WaterShovel();
	public static final ElementalItem leafShovel = new LeafShovel();
	public static final ElementalItem airShovel = new AirShovel();
	public static final ElementalItem earthShovel = new EarthShovel();
	public static final ElementalItem enderShovel = new EnderShovel();
	public static final ElementalItem plainShovel = new PlainShovel();

	// armor
	public static final ElementalItem fireHelmet = new FireArmor(HEAD);
	public static final ElementalItem fireChestplate = new FireArmor(CHEST);
	public static final ElementalItem fireLeggings = new FireArmor(LEGS);
	public static final ElementalItem fireBoots = new FireArmor(FEET);

	public static final ElementalItem iceHelmet = new IceArmor(HEAD);
	public static final ElementalItem iceChestplate = new IceArmor(CHEST);
	public static final ElementalItem iceLeggings = new IceArmor(LEGS);
	public static final ElementalItem iceBoots = new IceArmor(FEET);

	public static final ElementalItem waterHelmet = new WaterArmor(HEAD);
	public static final ElementalItem waterChestplate = new WaterArmor(CHEST);
	public static final ElementalItem waterLeggings = new WaterArmor(LEGS);
	public static final ElementalItem waterBoots = new WaterArmor(FEET);

	public static final ElementalItem leafHelmet = new LeafArmor(HEAD);
	public static final ElementalItem leafChestplate = new LeafArmor(CHEST);
	public static final ElementalItem leafLeggings = new LeafArmor(LEGS);
	public static final ElementalItem leafBoots = new LeafArmor(FEET);

	public static final ElementalItem earthHelmet = new EarthArmor(HEAD);
	public static final ElementalItem earthChestplate = new EarthArmor(CHEST);
	public static final ElementalItem earthLeggings = new EarthArmor(LEGS);
	public static final ElementalItem earthBoots = new EarthArmor(FEET);

	public static final ElementalItem airHelmet = new AirArmor(HEAD);
	public static final ElementalItem airChestplate = new AirArmor(CHEST);
	public static final ElementalItem airLeggings = new AirArmor(LEGS);
	public static final ElementalItem airBoots = new AirArmor(FEET);

	public static final ElementalItem enderHelmet = new EnderArmor(HEAD);
	public static final ElementalItem enderChestplate = new EnderArmor(CHEST);
	public static final ElementalItem enderLeggings = new EnderArmor(LEGS);
	public static final ElementalItem enderBoots = new EnderArmor(FEET);

	public static final ElementalItem plainHelmet = new PlainArmor(HEAD);
	public static final ElementalItem plainChestplate = new PlainArmor(CHEST);
	public static final ElementalItem plainLeggings = new PlainArmor(LEGS);
	public static final ElementalItem plainBoots = new PlainArmor(FEET);

	// arrows
	public static final ElementalItem fireArrow = new BaseArrow(FIRE);
	public static final ElementalItem iceArrow = new BaseArrow(ICE);
	public static final ElementalItem waterArrow = new BaseArrow(WATER);
	public static final ElementalItem leafArrow = new BaseArrow(LEAF);
	public static final ElementalItem airArrow = new BaseArrow(AIR);
	public static final ElementalItem earthArrow = new BaseArrow(EARTH);
	public static final ElementalItem enderArrow = new BaseArrow(ENDER);
	public static final ElementalItem plainArrow = new BaseArrow(PLAIN);

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
