package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.util.ElementalUtils;

public abstract class BaseEntityArrow extends AbstractArrowEntity {

	private final ElementalTypes type;

	public BaseEntityArrow(EntityType<? extends BaseEntityArrow> entityType, World world, ElementalTypes type) {
		super(entityType, world);
		this.type = type;
	}

	public BaseEntityArrow(EntityType<? extends BaseEntityArrow> entityType, World worldIn, double x, double y, double z, ElementalTypes type) {
		super(entityType, x, y, z, worldIn);
		this.type = type;
	}

	protected BaseEntityArrow(EntityType<? extends BaseEntityArrow> entityType, World world, LivingEntity shooter, ElementalTypes type) {
		super(entityType, shooter, world);
		this.type = type;
	}

	
	
	@Override
	public IPacket<?> getAddEntityPacket() {
		// this is required to render the arrow. Without this, the arrow won't be spawned client side
		return NetworkHooks.getEntitySpawningPacket(this);
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

	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(ElementalUtils.getArrowItemForType(this.type));
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
