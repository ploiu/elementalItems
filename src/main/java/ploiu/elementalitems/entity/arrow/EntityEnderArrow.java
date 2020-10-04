package ploiu.elementalitems.entity.arrow;

import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public class EntityEnderArrow extends BaseEntityArrow {
	public EntityEnderArrow(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z, ElementalTypes.ENDER);
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
