package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;

public class EntityEnderArrow extends BaseEntityArrow {
	public EntityEnderArrow(World worldIn, double x, double y, double z) {
		super(ElementalItemsEntityRegistry.enderArrowEntity, worldIn, x, y, z, ElementalTypes.ENDER);
	}

	public EntityEnderArrow(EntityType<? extends BaseEntityArrow> entityType, World world) {
		super(entityType, world, ElementalTypes.ENDER);
	}

	public EntityEnderArrow(World worldIn, LivingEntity shooter, ElementalTypes type) {
		super(ElementalItemsEntityRegistry.enderArrowEntity, worldIn, shooter, type);
	}
	
	@Override
	public void hitEntity(EntityRayTraceResult rayTraceResult) {
		// TODO teleport entity
	}

	@Override
	public void hitBlock(BlockRayTraceResult rayTraceResult) {
		// TODO teleport user
	}
}
