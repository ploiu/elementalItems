package ploiu.elementalitems.entity.arrow;

import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class EntityPlainArrow extends BaseEntityArrow {
	public EntityPlainArrow(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z, ElementalTypes.PLAIN);
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