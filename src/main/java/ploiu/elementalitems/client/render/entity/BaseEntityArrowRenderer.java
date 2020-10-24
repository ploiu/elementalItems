package ploiu.elementalitems.client.render.entity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ploiu.elementalitems.entity.arrow.BaseEntityArrow;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class BaseEntityArrowRenderer extends ArrowRenderer<BaseEntityArrow> {

	public BaseEntityArrowRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public ResourceLocation getEntityTexture(BaseEntityArrow entity) {
		String base = "elementalitems:textures/entity/projectiles/arrow_";
		return new ResourceLocation(base + entity.getElementalType().getTypeName() + ".png");
	}
}
