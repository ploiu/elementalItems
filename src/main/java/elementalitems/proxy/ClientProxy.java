package elementalitems.proxy;

import elementalitems.entities.render.RenderBaseArrow;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;


public class ClientProxy extends CommonProxy {
	@Override
	public void registerItemRenderer(Item item, int meta) {
		//use the ModelLoader to set the custom model resource
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	@Override
	public void registerEntityRenderer(Class<? extends Entity> entityClass) {
		if(EntityArrow.class.isAssignableFrom(entityClass)) {
			RenderingRegistry.registerEntityRenderingHandler(entityClass, (RenderManager manager) -> (Render<Entity>) new RenderBaseArrow(manager));
		}
	}
}
