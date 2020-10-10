package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;

public class EntityWaterArrow extends BaseEntityArrow {
	public EntityWaterArrow(World worldIn, double x, double y, double z) {
		super(ElementalItemsEntityRegistry.waterArrowEntity, worldIn, x, y, z, ElementalTypes.WATER);
	}

	public EntityWaterArrow(EntityType<? extends BaseEntityArrow> entityType, World world) {
		super(entityType, world, ElementalTypes.WATER);
	}

	public EntityWaterArrow(World worldIn, LivingEntity shooter) {
		super(ElementalItemsEntityRegistry.waterArrowEntity, worldIn, shooter, ElementalTypes.WATER);
	}
	
	@Override
	public void hitEntity(EntityRayTraceResult rayTraceResult) {

	}

	@Override
	public void hitBlock(BlockRayTraceResult rayTraceResult) {

	}
}
