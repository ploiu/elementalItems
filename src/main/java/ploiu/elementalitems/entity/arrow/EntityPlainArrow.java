package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;

public class EntityPlainArrow extends BaseEntityArrow {
	public EntityPlainArrow(World worldIn, double x, double y, double z) {
		super(ElementalItemsEntityRegistry.plainArrowEntity, worldIn, x, y, z, ElementalTypes.PLAIN);
	}

	public EntityPlainArrow(EntityType<? extends BaseEntityArrow> entityType, World world) {
		super(entityType, world, ElementalTypes.PLAIN);
	}

	public EntityPlainArrow(World worldIn, LivingEntity shooter) {
		super(ElementalItemsEntityRegistry.plainArrowEntity, worldIn, shooter, ElementalTypes.PLAIN);
	}
	
	@Override
	public void hitEntity(EntityRayTraceResult rayTraceResult) {
		// no-op
	}

	@Override
	public void hitBlock(BlockRayTraceResult rayTraceResult) {
		// no-op
	}
}
