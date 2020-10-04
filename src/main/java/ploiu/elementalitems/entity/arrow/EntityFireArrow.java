package ploiu.elementalitems.entity.arrow;

import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class EntityFireArrow extends BaseEntityArrow {
	public EntityFireArrow(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z, ElementalTypes.FIRE);
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
