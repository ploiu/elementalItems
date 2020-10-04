package ploiu.elementalitems.entity.arrow;

import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class EntityWaterArrow extends BaseEntityArrow {
	public EntityWaterArrow(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z, ElementalTypes.WATER);
	}

	@Override
	public void hitEntity(EntityRayTraceResult rayTraceResult) {

	}

	@Override
	public void hitBlock(BlockRayTraceResult rayTraceResult) {

	}
}
