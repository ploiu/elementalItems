package ploiu.elementalitems.client.render.entity;

import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;

public class ElementalItemsRenderingRegistry {

	public static void registerRenderers() {
		ElementalItemsEntityRegistry.entities.forEach(entityType -> RenderingRegistry.registerEntityRenderingHandler((EntityType) entityType, BaseEntityArrowRenderer::new));
	}
}
