package ploiu.elementalitems.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.arrow.*;
import ploiu.elementalitems.entity.flamethrower.FlamethrowerEntity;
import ploiu.elementalitems.util.ElementalUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class ElementalItemsEntityRegistry {
	public static List<EntityType<?>> entities = new ArrayList<>();

	public static EntityType<FlamethrowerEntity> flamethrowerEntity = (EntityType<FlamethrowerEntity>) EntityType.Builder.<FlamethrowerEntity>create(FlamethrowerEntity::new, EntityClassification.MISC)
			                                                                                                   .setShouldReceiveVelocityUpdates(true)
			                                                                                                   .setTrackingRange(24)
			                                                                                                   .setUpdateInterval(60)
			                                                                                                   .build("entity_flamethrower")
			                                                                                                   .setRegistryName("elementalitems", "entity_flamethrower");

	public static EntityType<EntityPlainArrow> plainArrowEntity = buildArrowEntityType(ElementalTypes.PLAIN, EntityPlainArrow.class);
	public static EntityType<EntityFireArrow> fireArrowEntity = buildArrowEntityType(ElementalTypes.FIRE, EntityFireArrow.class);
	public static EntityType<EntityIceArrow> iceArrowEntity = buildArrowEntityType(ElementalTypes.ICE, EntityIceArrow.class);
	public static EntityType<EntityWaterArrow> waterArrowEntity = buildArrowEntityType(ElementalTypes.WATER, EntityWaterArrow.class);
	public static EntityType<EntityLeafArrow> leafArrowEntity = buildArrowEntityType(ElementalTypes.LEAF, EntityLeafArrow.class);
	public static EntityType<EntityEarthArrow> earthArrowEntity = buildArrowEntityType(ElementalTypes.EARTH, EntityEarthArrow.class);
	public static EntityType<EntityAirArrow> airArrowEntity = buildArrowEntityType(ElementalTypes.AIR, EntityAirArrow.class);
	public static EntityType<EntityEnderArrow> enderArrowEntity = buildArrowEntityType(ElementalTypes.ENDER, EntityEnderArrow.class);

	private static <T extends BaseEntityArrow> EntityType<T> buildArrowEntityType(ElementalTypes type, Class<T> arrowClass) {
		EntityType<T> arrowEntity = (EntityType<T>) EntityType.Builder.<T>create((entityType, world) -> {
			try {
				return arrowClass.getConstructor(EntityType.class, World.class).newInstance(entityType, world);
			} catch(Exception e) {
				throw new RuntimeException("FAILED TO INSTANTIATE ARROWS");
			}
		}, EntityClassification.MISC)
				                       .setShouldReceiveVelocityUpdates(true)
				                       .setTrackingRange(24)
				                       .setUpdateInterval(60)
				                       .build(String.format("entity_arrow_%s", type.getTypeName()))
				                       .setRegistryName(String.format("elementalitems:entity_arrow_%s", type.getTypeName()));
		entities.add(arrowEntity);
		return arrowEntity;
	}

	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(
				flamethrowerEntity
		);
		entities.forEach(event.getRegistry()::register);
	}
}
