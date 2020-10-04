package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;

public abstract class BaseEntityArrow extends ArrowEntity {

	private final ElementalTypes type;

	public BaseEntityArrow(World worldIn, double x, double y, double z, ElementalTypes type) {
		super(worldIn, x, y, z);
		this.type = type;
	}

	public ElementalTypes getElementalType() {
		return this.type;
	}

	@Override
	protected void onHit(RayTraceResult raytraceResultIn) {
		super.onHit(raytraceResultIn);
		if(raytraceResultIn.getType() == RayTraceResult.Type.BLOCK) {
			this.hitBlock((BlockRayTraceResult) raytraceResultIn);
		} else if(raytraceResultIn.getType() == RayTraceResult.Type.ENTITY) {
			this.hitEntity((EntityRayTraceResult) raytraceResultIn);
		}
	}

	/**
	 * called when an entity has been hit with this arrow
	 *
	 * @param rayTraceResult
	 */
	public abstract void hitEntity(EntityRayTraceResult rayTraceResult);

	/**
	 * called when a block is hit with this arrow
	 *
	 * @param rayTraceResult
	 */
	public abstract void hitBlock(BlockRayTraceResult rayTraceResult);
}
