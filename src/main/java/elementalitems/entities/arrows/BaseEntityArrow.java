package elementalitems.entities.arrows;

import elementalitems.ElementalType;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * The type Base entity arrow.
 */
public abstract class BaseEntityArrow extends EntityArrow implements ElementalArrow {

	/**
	 * The Type.
	 */
	@SuppressWarnings("InstanceVariableMayNotBeInitialized")
	protected ElementalType type;
	private boolean hasAppliedLandingEffect;

	/**
	 * Instantiates a new Base entity arrow.
	 *
	 * @param worldIn the world in
	 * @param shooter the shooter
	 * @param type    the type
	 */
	public BaseEntityArrow(World worldIn, EntityLivingBase shooter, ElementalType type) {
		super(worldIn, shooter);
		this.type = type;
		this.hasAppliedLandingEffect = false;
		this.setDamage(4.0D);
	}

	/**
	 * Instantiates a new Base entity arrow.
	 *
	 * @param world the world
	 * @param x     the x
	 * @param y     the y
	 * @param z     the z
	 * @param type  the type
	 */
	public BaseEntityArrow(World world, double x, double y, double z, ElementalType type) {
		super(world, x, y, z);
		this.hasAppliedLandingEffect = false;
		this.type = type;
	}

	public ElementalType getType() {
		return this.type;
	}

	/**
	 * Instantiates a new Base entity arrow.
	 *
	 * @param world the world
	 */
	public BaseEntityArrow(World world) {
		super(world);
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack((Item) EntityUtils.getInstance().getArrowItemFromElementalType(this.type));
	}

	@Override
	public abstract void applyEffectOnEntity(EntityLivingBase target);

	/**
	 * called when this arrow is in the ground for the first time
	 */
	@Override
	public void applyLandingEffect(BlockPos positionHit, EnumFacing sideHit) {
		this.hasAppliedLandingEffect = true;
	}

	@Override
	protected void arrowHit(EntityLivingBase living) {
		super.arrowHit(living);
		this.applyEffectOnEntity(living);
	}

	@Override
	public void onHit(RayTraceResult result) {
		super.onHit(result);
		// if this is in the ground, apply the landing effect
		if(!this.getEntityWorld().isRemote && result.typeOfHit == RayTraceResult.Type.BLOCK && !this.hasAppliedLandingEffect) {
			this.applyLandingEffect(result.getBlockPos(), result.sideHit);
		}
	}
}
