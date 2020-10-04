package ploiu.elementalitems.entity.arrow;

import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class EntityEarthArrow extends BaseEntityArrow {
	public EntityEarthArrow(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z, ElementalTypes.EARTH);
	}

	@Override
	public void hitEntity(EntityRayTraceResult rayTraceResult) {
		// TODO
	}

	@Override
	public void hitBlock(BlockRayTraceResult rayTraceResult) {
		// no-op or maybe TODO sink the block into the ground because the arrow is heavy
	}
}
