package ploiu.elementalitems.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import org.apache.http.client.entity.EntityBuilder;
import ploiu.elementalitems.entity.flamethrower.FlamethrowerEntity;

@SuppressWarnings("unchecked")
public class ElementalItemsEntityRegistry {
	public static EntityType<FlamethrowerEntity> flamethrowerEntity = (EntityType<FlamethrowerEntity>) EntityType.Builder.<FlamethrowerEntity>create(FlamethrowerEntity::new, EntityClassification.MISC)
			                                                                                                   .setShouldReceiveVelocityUpdates(true)
			                                                                                                   .setTrackingRange(24)
			                                                                                                   .setUpdateInterval(60)
			                                                                                                   .build("entity_flamethrower")
			                                                                                                   .setRegistryName("elementalitems", "entity_flamethrower");

	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(
				flamethrowerEntity
		);
	}
}
