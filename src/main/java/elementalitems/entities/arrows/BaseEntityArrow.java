package elementalitems.entities.arrows;

import elementalitems.ElementalTypes;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MultiPartEntityPart;
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
	 * The type associated with this entity; defaults to PLAIN
	 */
	protected ElementalTypes type = ElementalTypes.PLAIN;
	private boolean hasAppliedLandingEffect;
	private boolean hasAppliedEntityCollisionEffect;

	/**
	 * Instantiates a new Base entity arrow.
	 *
	 * @param worldIn the world in
	 * @param shooter the shooter
	 * @param type    the type
	 */
	public BaseEntityArrow(World worldIn, EntityLivingBase shooter, ElementalTypes type) {
		super(worldIn, shooter);
		this.type = type;
		this.hasAppliedLandingEffect = false;
		this.hasAppliedEntityCollisionEffect = false;
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
	public BaseEntityArrow(World world, double x, double y, double z, ElementalTypes type) {
		super(world, x, y, z);
		this.hasAppliedLandingEffect = false;
		this.hasAppliedEntityCollisionEffect = false;
		this.type = type;
	}

	/**
	 * Instantiates a new Base entity arrow.
	 *
	 * @param world the world
	 */
	public BaseEntityArrow(World world) {
		super(world);
	}

	public ElementalTypes getType() {
		return this.type;
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
	public void onHit(RayTraceResult result) {
		super.onHit(result);
		// if this is in the ground, apply the landing effect
		if(!this.getEntityWorld().isRemote) {
			// if it's in the ground
			if(result.typeOfHit == RayTraceResult.Type.BLOCK && !this.hasAppliedLandingEffect) {
				this.applyLandingEffect(result.getBlockPos(), result.sideHit);
				// previously I thought that overrideing arrowHit would work, but nope
			} else if(!this.hasAppliedEntityCollisionEffect && result.typeOfHit == RayTraceResult.Type.ENTITY) {
				if(EntityUtils.getInstance().isValidEntityLivingBase(result.entityHit)) {
					// call the effect directly on entityHit
					this.applyEffectOnEntity((EntityLivingBase) result.entityHit);
					// the EnderDragon is actually made up of multiple parts, and this helps handle that
				} else if(result.entityHit instanceof MultiPartEntityPart && EntityUtils.getInstance().getParentEntityFromMultipart((MultiPartEntityPart) result.entityHit) != null) {
					this.applyEffectOnEntity(EntityUtils.getInstance().getParentEntityFromMultipart((MultiPartEntityPart) result.entityHit));
				}
			}
		}
	}

	@Override
	protected void arrowHit(EntityLivingBase living) {
		if(!this.hasAppliedEntityCollisionEffect) {
			this.applyEffectOnEntity(living);
			this.hasAppliedEntityCollisionEffect = true;
		}
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack((Item) EntityUtils.getInstance().getArrowItemFromElementalType(this.type));
	}

	@Override
	public String toString() {
		return "BaseEntityArrow{" +
				       "type=" + this.type +
				       ", hasAppliedLandingEffect=" + this.hasAppliedLandingEffect +
				       '}';
	}
}
