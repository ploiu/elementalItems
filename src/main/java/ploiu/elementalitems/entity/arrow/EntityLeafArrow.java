package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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

	public EntityLeafArrow(World worldIn, LivingEntity shooter) {
		super(ElementalItemsEntityRegistry.leafArrowEntity, worldIn, shooter, ElementalTypes.LEAF);
	}

	@Override
	public void hitEntity(EntityRayTraceResult rayTraceResult) {
		// TODO figure out how to do extra damage. Mobs are immune to damage immediately after getting hit by an arrow
	}

	@Override
	public void hitBlock(BlockRayTraceResult rayTraceResult) {
		// TODO
	}
}
