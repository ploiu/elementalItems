package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;

public class EntityFireArrow extends BaseEntityArrow {
	public EntityFireArrow(World worldIn, double x, double y, double z) {
		super(ElementalItemsEntityRegistry.fireArrowEntity, worldIn, x, y, z, ElementalTypes.FIRE);
	}

	public EntityFireArrow(EntityType<? extends BaseEntityArrow> entityType, World world) {
		super(entityType, world, ElementalTypes.FIRE);
	}

	public EntityFireArrow(World worldIn, LivingEntity shooter, ElementalTypes type) {
		super(ElementalItemsEntityRegistry.fireArrowEntity, worldIn, shooter, type);
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
