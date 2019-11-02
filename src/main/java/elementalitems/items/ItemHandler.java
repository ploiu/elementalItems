package elementalitems.items;

import elementalitems.ElementalItems;
import elementalitems.items.combat.armor.*;
import elementalitems.items.combat.arrows.BaseArrow;
import elementalitems.items.combat.swords.*;
import elementalitems.items.tools.axes.*;
import elementalitems.items.tools.pickaxes.*;
import elementalitems.items.tools.shovels.*;
import elementalitems.items.unique.Flamethrower;
import elementalitems.items.unique.Hammer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

import static elementalitems.ElementalTypes.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.text.TextFormatting.*;
import static net.minecraftforge.fml.common.registry.GameRegistry.addSmelting;

/**
 * The type Item handler.
 */
@SuppressWarnings("unused")
public class ItemHandler {
	//arrayList of base items to register
	public static final List<ElementalItem> items = new ArrayList<>();
	// the crystals
	public static ElementalItem plainCrystal;
	public static ElementalItem fireCrystal;
	public static ElementalItem waterCrystal;
	public static ElementalItem leafCrystal;
	public static ElementalItem iceCrystal;
	public static ElementalItem earthCrystal;
	public static ElementalItem airCrystal;
	public static ElementalItem enderCrystal;

	// swords
	public static ElementalItem fireSword;
	public static ElementalItem iceSword;
	public static ElementalItem waterSword;
	public static ElementalItem leafSword;
	public static ElementalItem airSword;
	public static ElementalItem earthSword;
	public static ElementalItem enderSword;
	public static ElementalItem lifeDeathSword;
	public static ElementalItem plainSword;
	// dual swords
	public static ElementalItem meteorSword; // fire and earth
	public static ElementalItem coldFireSword; // fire and ice
	public static ElementalItem steamSword; // fire and water
	public static ElementalItem typhoonSword; // water and air
	public static ElementalItem blizzardSword; // ice and air
	public static ElementalItem voidSword; // ice and ender
	public static ElementalItem jungleSword; // water and leaf
	public static ElementalItem glacialSword; // water and ice
	public static ElementalItem pollenSword; // leaf and air

	// pickaxes
	public static ElementalItem firePickaxe;
	public static ElementalItem icePickaxe;
	public static ElementalItem waterPickaxe;
	public static ElementalItem leafPickaxe;
	public static ElementalItem airPickaxe;
	public static ElementalItem earthPickaxe;
	public static ElementalItem enderPickaxe;
	public static ElementalItem plainPickaxe;

	// axes
	public static ElementalItem fireAxe;
	public static ElementalItem iceAxe;
	public static ElementalItem waterAxe;
	public static ElementalItem leafAxe;
	public static ElementalItem airAxe;
	public static ElementalItem earthAxe;
	public static ElementalItem enderAxe;
	public static ElementalItem plainAxe;

	// shovels
	public static ElementalItem fireShovel;
	public static ElementalItem iceShovel;
	public static ElementalItem waterShovel;
	public static ElementalItem leafShovel;
	public static ElementalItem airShovel;
	public static ElementalItem earthShovel;
	public static ElementalItem enderShovel;
	public static ElementalItem plainShovel;

	// armor
	public static ElementalItem fireHelmet;
	public static ElementalItem fireChestplate;
	public static ElementalItem fireLeggings;
	public static ElementalItem fireBoots;

	public static ElementalItem iceHelmet;
	public static ElementalItem iceChestplate;
	public static ElementalItem iceLeggings;
	public static ElementalItem iceBoots;

	public static ElementalItem waterHelmet;
	public static ElementalItem waterChestplate;
	public static ElementalItem waterLeggings;
	public static ElementalItem waterBoots;

	public static ElementalItem leafHelmet;
	public static ElementalItem leafChestplate;
	public static ElementalItem leafLeggings;
	public static ElementalItem leafBoots;

	public static ElementalItem earthHelmet;
	public static ElementalItem earthChestplate;
	public static ElementalItem earthLeggings;
	public static ElementalItem earthBoots;

	public static ElementalItem airHelmet;
	public static ElementalItem airChestplate;
	public static ElementalItem airLeggings;
	public static ElementalItem airBoots;

	public static ElementalItem enderHelmet;
	public static ElementalItem enderChestplate;
	public static ElementalItem enderLeggings;
	public static ElementalItem enderBoots;

	public static ElementalItem plainHelmet;
	public static ElementalItem plainChestplate;
	public static ElementalItem plainLeggings;
	public static ElementalItem plainBoots;

	// arrows
	public static ElementalItem fireArrow;
	public static ElementalItem iceArrow;
	public static ElementalItem waterArrow;
	public static ElementalItem leafArrow;
	public static ElementalItem airArrow;
	public static ElementalItem earthArrow;
	public static ElementalItem enderArrow;
	public static ElementalItem plainArrow;

	// unique items
	public static ElementalItem flamethrower;
	public static ElementalItem hammer;

	private ItemHandler() {
	}

	public static void initializeAllItems() {
		plainCrystal = new BaseCrystal(PLAIN);
		fireCrystal = new BaseCrystal(FIRE);
		waterCrystal = new BaseCrystal(WATER);
		leafCrystal = new BaseCrystal(LEAF);
		iceCrystal = new BaseCrystal(ICE);
		earthCrystal = new BaseCrystal(EARTH);
		airCrystal = new BaseCrystal(AIR);
		enderCrystal = new BaseCrystal(ENDER);
		// swords
		fireSword = new FireSword();
		iceSword = new IceSword();
		waterSword = new WaterSword();
		leafSword = new LeafSword();
		airSword = new AirSword();
		earthSword = new EarthSword();
		enderSword = new EnderSword();
		lifeDeathSword = new LifeDeathSword();
		plainSword = new PlainSword();
		// dual swords
		// Thanks for the quote, Will Smith!
		meteorSword = new DualSwordBuilder(FIRE, EARTH).tooltip(DARK_RED + "That's " + RED + "Hot!").build();
		coldFireSword = new DualSwordBuilder(ICE, FIRE).tooltip(AQUA + "Freezer " + RED + "Burn!").build();
		steamSword = new DualSwordBuilder(FIRE, WATER).tooltip(GRAY + "Best Sale " + RED + UNDERLINE + ITALIC + "Ever!").build();
		typhoonSword = new DualSwordBuilder(WATER, AIR).tooltip(BLUE + "With All The Force Of A Great Typhoon!").build();
		blizzardSword = new DualSwordBuilder(ICE, AIR).tooltip(AQUA + "WoW.").build();
		voidSword = new DualSwordBuilder(ICE, ENDER).tooltip(DARK_GRAY + "Void Where Prohibited!").build();
		jungleSword = new DualSwordBuilder(LEAF, WATER).tooltip(BLUE + "Fun" + GRAY + " And " + GREEN + "Games!").build();
		glacialSword = new DualSwordBuilder(ICE, WATER).tooltip(BLUE + "You Want " + AQUA + "Ice " + BLUE + "With That?").build();
		pollenSword = new DualSwordBuilder(LEAF, AIR).tooltip(GREEN + "ACHOO!").build();
		
		// pickaxes
		firePickaxe = new FirePickaxe();
		icePickaxe = new IcePickaxe();
		waterPickaxe = new WaterPickaxe();
		leafPickaxe = new LeafPickaxe();
		airPickaxe = new AirPickaxe();
		earthPickaxe = new EarthPickaxe();
		enderPickaxe = new EnderPickaxe();
		plainPickaxe = new PlainPickaxe();
		// axes
		fireAxe = new FireAxe();
		iceAxe = new IceAxe();
		waterAxe = new WaterAxe();
		leafAxe = new LeafAxe();
		airAxe = new AirAxe();
		earthAxe = new EarthAxe();
		enderAxe = new EnderAxe();
		plainAxe = new PlainAxe();
		// shovels
		fireShovel = new FireShovel();
		iceShovel = new IceShovel();
		waterShovel = new WaterShovel();
		leafShovel = new LeafShovel();
		airShovel = new AirShovel();
		earthShovel = new EarthShovel();
		enderShovel = new EnderShovel();
		plainShovel = new PlainShovel();
		// armor
		fireHelmet = new FireArmor(HEAD);
		fireChestplate = new FireArmor(CHEST);
		fireLeggings = new FireArmor(LEGS);
		fireBoots = new FireArmor(FEET);
		iceHelmet = new IceArmor(HEAD);
		iceChestplate = new IceArmor(CHEST);
		iceLeggings = new IceArmor(LEGS);
		iceBoots = new IceArmor(FEET);
		waterHelmet = new WaterArmor(HEAD);
		waterChestplate = new WaterArmor(CHEST);
		waterLeggings = new WaterArmor(LEGS);
		waterBoots = new WaterArmor(FEET);
		leafHelmet = new LeafArmor(HEAD);
		leafChestplate = new LeafArmor(CHEST);
		leafLeggings = new LeafArmor(LEGS);
		leafBoots = new LeafArmor(FEET);
		earthHelmet = new EarthArmor(HEAD);
		earthChestplate = new EarthArmor(CHEST);
		earthLeggings = new EarthArmor(LEGS);
		earthBoots = new EarthArmor(FEET);
		airHelmet = new AirArmor(HEAD);
		airChestplate = new AirArmor(CHEST);
		airLeggings = new AirArmor(LEGS);
		airBoots = new AirArmor(FEET);
		enderHelmet = new EnderArmor(HEAD);
		enderChestplate = new EnderArmor(CHEST);
		enderLeggings = new EnderArmor(LEGS);
		enderBoots = new EnderArmor(FEET);
		plainHelmet = new PlainArmor(HEAD);
		plainChestplate = new PlainArmor(CHEST);
		plainLeggings = new PlainArmor(LEGS);
		plainBoots = new PlainArmor(FEET);
		// arrows
		fireArrow = new BaseArrow(FIRE);
		iceArrow = new BaseArrow(ICE);
		waterArrow = new BaseArrow(WATER);
		leafArrow = new BaseArrow(LEAF);
		airArrow = new BaseArrow(AIR);
		earthArrow = new BaseArrow(EARTH);
		enderArrow = new BaseArrow(ENDER);
		plainArrow = new BaseArrow(PLAIN);
		// unique items
		flamethrower = new Flamethrower();
		hammer = new Hammer();
	}

	public static void registerSmelting() {
		addSmelting(new ItemStack((Item) plainCrystal), new ItemStack((Item) fireCrystal), 5);
		addSmelting(new ItemStack((Item) iceCrystal), new ItemStack((Item) waterCrystal), 5);
	}

	/**
	 * Registers all of our items
	 *
	 * @param registry the forge registry we are registering everything with
	 */
	public static void register(IForgeRegistry<Item> registry) {
		ElementalItems.logger.log(Level.INFO, "Registering items...");
		initializeAllItems();
		//register all of our items with the registry
		registry.registerAll(items.toArray(new Item[]{}));
		registerSmelting();
	}

	/**
	 * Registers all of our item's models
	 */
	public static void registerModels() {
		ElementalItems.logger.log(Level.INFO, "Registering item models...");
		items.forEach((ElementalItem item) -> ElementalItems.proxy.registerItemRenderer((Item) item, 0));
	}
}
