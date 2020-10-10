package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;

public class EntityIceArrow extends BaseEntityArrow {
	public EntityIceArrow(World worldIn, double x, double y, double z) {
		super(ElementalItemsEntityRegistry.iceArrowEntity, worldIn, x, y, z, ElementalTypes.ICE);
	}

	public EntityIceArrow(EntityType<? extends BaseEntityArrow> entityType, World world) {
		super(entityType, world, ElementalTypes.ICE);
	}

	public EntityIceArrow(World worldIn, LivingEntity shooter, ElementalTypes type) {
		super(ElementalItemsEntityRegistry.iceArrowEntity, worldIn, shooter, type);
	}
	
	@Override
	public void hitEntity(EntityRayTraceResult rayTraceResult) {
		// TODO
	}

	@Override
	public void hitBlock(BlockRayTraceResult rayTraceResult) {
		// TODO
	}
}
