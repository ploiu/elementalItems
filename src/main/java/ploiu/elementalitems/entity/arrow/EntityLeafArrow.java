package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;

public class EntityLeafArrow extends BaseEntityArrow {
	public EntityLeafArrow(World worldIn, double x, double y, double z) {
		super(ElementalItemsEntityRegistry.leafArrowEntity, worldIn, x, y, z, ElementalTypes.LEAF);
	}

	public EntityLeafArrow(EntityType<? extends BaseEntityArrow> entityType, World world) {
		super(entityType, world, ElementalTypes.LEAF);
	}

	public EntityLeafArrow(World worldIn, LivingEntity shooter, ElementalTypes type) {
		super(ElementalItemsEntityRegistry.leafArrowEntity, worldIn, shooter, type);
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
