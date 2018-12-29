package elementalitems.entities;

import elementalitems.ElementalItems;
import elementalitems.ElementalTypes;
import elementalitems.entities.arrows.*;
import elementalitems.util.EntityUtils;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static elementalitems.ElementalItems.MOD_ID;
import static elementalitems.ElementalItems.logger;
import static org.apache.logging.log4j.Level.INFO;

public class EntityHandler {
	 static final Map<String, Class<? extends Entity>> entities = new HashMap<>();

	// there's no real easy way to do this, so use a static initializer...
	static {
		// add our entity arrow classes to our entities
		entities.put("arrow_fire", EntityFireArrow.class);
		entities.put("arrow_water", EntityWaterArrow.class);
		entities.put("arrow_ice", EntityIceArrow.class);
		entities.put("arrow_leaf", EntityLeafArrow.class);
		entities.put("arrow_air", EntityAirArrow.class);
		entities.put("arrow_earth", EntityEarthCombatArrow.class);
		entities.put("arrow_ender", EntityEnderArrow.class);
		entities.put("arrow_plain", EntityPlainArrow.class);
	}

	public static void register() {
		logger.log(INFO, "Registering entities...");
		// must use this as we can't modify a value outside of a lambda, but these are ok
		AtomicInteger atomicInteger = new AtomicInteger(0);
		entities.forEach((String registryName, Class<? extends Entity> entityClass) -> EntityRegistry.registerModEntity(new ResourceLocation(MOD_ID + ":" + registryName), entityClass, registryName, atomicInteger.getAndIncrement(), ElementalItems.instance, 64, 1, true));
		setDispenserBehaviorForArrows();
		logger.log(INFO, "Finished registering entities");
	}

	public static void registerRenderers() {
		logger.log(INFO, "Registering entity renderers...");
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		// iterate through our entities and register them for rendering
		entities.values().forEach(value -> ElementalItems.proxy.registerEntityRenderer(value));
		logger.log(INFO, "Finished registering entity renderers");
	}

	public static void setDispenserBehaviorForArrows() {
		/* modify dispenser handling for our arrows by looping through all of our types, getting the arrow associated 
		with that type, and getting the arrow entity associated with that type */
		Arrays.stream(ElementalTypes.values()).forEach(type -> {
			// get the BaseArrow by the elemental type
			Item arrowToRegister = (Item) EntityUtils.getInstance().getArrowItemFromElementalType(type);
			// now register the behavior of the item
			BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(arrowToRegister, new BehaviorProjectileDispense() {
				@Override
				protected IProjectile getProjectileEntity(World world, IPosition position, ItemStack stack) {
					// get the BaseEntityArrow for the current type
					BaseEntityArrow registeredEntityArrow = EntityUtils.getInstance().createArrow(type, world, position.getX(), position.getY(), position.getZ());
					registeredEntityArrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
					return registeredEntityArrow;
				}
			});
		});
	}
}
