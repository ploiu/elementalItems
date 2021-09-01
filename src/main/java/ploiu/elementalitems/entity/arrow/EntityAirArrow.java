package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;

/**
 * TODO make super fast
 */
public class EntityAirArrow extends BaseEntityArrow {
	public EntityAirArrow(World worldIn, double x, double y, double z) {
		super(ElementalItemsEntityRegistry.airArrowEntity, worldIn, x, y, z, ElementalTypes.AIR);
	}

	public EntityAirArrow(EntityType<? extends BaseEntityArrow> entityType, World world) {
		super(entityType, world, ElementalTypes.AIR);
	}

	public EntityAirArrow(World worldIn, LivingEntity shooter) {
		super(ElementalItemsEntityRegistry.airArrowEntity, worldIn, shooter, ElementalTypes.AIR);
	}

	@Override
	public void hitEntity(EntityRayTraceResult rayTraceResult) {
		// TODO
	}

	@Override
	public void hitBlock(BlockRayTraceResult rayTraceResult) {
		// no-op
	}
}
