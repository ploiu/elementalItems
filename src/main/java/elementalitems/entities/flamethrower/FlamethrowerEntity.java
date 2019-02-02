package elementalitems.entities.flamethrower;

import elementalitems.ElementalItemsConfig;
import elementalitems.util.EntityUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FlamethrowerEntity extends EntityThrowable {

	protected EntityLivingBase ownerEntity;

	public FlamethrowerEntity(World worldIn) {
		super(worldIn);
	}

	public FlamethrowerEntity(World world, @Nonnull EntityPlayer ownerEntity) {
		super(world, ownerEntity.posX, ownerEntity.posY + ownerEntity.getEyeHeight() - 0.10000000149011612D, ownerEntity.posZ);
		this.setSize(0.5f, 0.5f);
		this.setNoGravity(true);
		this.ownerEntity = ownerEntity;
		this.thrower = this.ownerEntity;
	}

	public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
		super.shoot(entityThrower, rotationPitchIn, rotationYawIn, pitchOffset, velocity, inaccuracy);
		if(EntityUtils.getInstance().isValidEntityLivingBase(entityThrower)) {
			this.thrower = (EntityLivingBase) entityThrower;
			this.ownerEntity = (EntityLivingBase) entityThrower;
		}
		this.isImmuneToFire = true;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		// this doesn't work if the world isn't a WorldServer since on the client side, this.thrower is null
		if(!this.world.isRemote) {
			// set any impacted mobs on fire, and any impacted blocks on fire
			if(result.typeOfHit == RayTraceResult.Type.ENTITY && EntityUtils.getInstance().isValidEntityLivingBase(result.entityHit) && !result.entityHit.equals(this.ownerEntity)) {
				this.attackEntityOnDirectHit((EntityLivingBase) result.entityHit);
			} else if(result.typeOfHit == RayTraceResult.Type.BLOCK) {
				this.setHitBlockOnFireIfConfigAllowsIt(result);
				// if the block is not passable, kill this entity after spawning a bunch of particles
				if(this.canNotPassThroughHitBlock(result)) {
					// spawn a bunch of particles
					if(this.world instanceof WorldServer) {
						WorldServer worldServer = (WorldServer) this.world;
						worldServer.spawnParticle(EnumParticleTypes.FLAME, true, this.posX, this.posY, this.posZ, 10, this.width, this.height, this.width, this.rand.nextGaussian() / 10, 0);
					}
					this.setDead();
				}
			}
		}
	}

	/**
	 * checks our config file, and if it's set to allow this entity to set blocks on fire, set the block this entity hit on fire
	 *
	 * @param result the {@link RayTraceResult} that has all the information we need
	 */
	private void setHitBlockOnFireIfConfigAllowsIt(RayTraceResult result) {
		if(ElementalItemsConfig.shouldFlamethrowerSetBlocksOnFire) {
			BlockPos positionHit = result.getBlockPos();
			IBlockState fire = Blocks.FIRE.getDefaultState();
			// set the block this is on on fire
			if(fire.getBlock().canPlaceBlockOnSide(this.world, positionHit, result.sideHit) && this.world.getBlockState(result.getBlockPos()).getMaterial().getCanBurn() && (!this.world.getBlockState(positionHit.offset(result.sideHit)).getMaterial().isLiquid())) {
				BlockPos blockPosForFire = positionHit.offset(result.sideHit);
				this.world.setBlockState(blockPosForFire, fire, 11);
			}
		}
	}

	/**
	 * checks if the block we hit blocks movement, and returns the result of that check
	 *
	 * @param result the {@link RayTraceResult} that has all the information we need
	 * @return true if we can not pass through the block, false otherwise
	 */
	private boolean canNotPassThroughHitBlock(RayTraceResult result) {
		// get the block at the result's block position
		BlockPos hitBlockPos = result.getBlockPos();
		IBlockState hitState = this.world.getBlockState(hitBlockPos);
		return hitState.getMaterial().blocksMovement();
	}

	/**
	 * attacks the passed {@link EntityLivingBase} that we collided with, and sets our ownerEntity to be the one who attacked it
	 *
	 * @param hitEntity the entity we collided with
	 */
	private void attackEntityOnDirectHit(@Nonnull EntityLivingBase hitEntity) {
		// set it on fire first to modify the drops
		hitEntity.setFire(10);
		hitEntity.attackEntityFrom(DamageSource.IN_FIRE, 10.0f);
		// now make the entity mad at the user
		if(this.ownerEntity != null) {
			hitEntity.setLastAttackedEntity(this.ownerEntity);
		}
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		// kill this entity if it's existed for more than 20 ticks
		if(this.ticksExisted >= 20 || this.inWater) {
			this.setDead();
			return;
		}
		// spawn fire particles after a little bit
		if(this.ticksExisted >= 2 && this.world instanceof WorldServer) {
			WorldServer worldServer = (WorldServer) this.world;
			worldServer.spawnParticle(EnumParticleTypes.FLAME, true, this.posX, this.posY, this.posZ, 3, this.width / 4, 0, 0, 0.0d, 0);
			// grow our bounding box, capture any entities that aren't this and aren't our owner in it, and set them on fire
			AxisAlignedBB expandedBoundingBox = this.getEntityBoundingBox().grow(1, 1, 1);
			worldServer.getEntitiesInAABBexcluding(this.ownerEntity, expandedBoundingBox, input -> !this.equals(input)).forEach(this::setEntityOnFire);
		}
	}

	@Override
	public boolean isInvisible() {
		return true;
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		return false;
	}

	/**
	 * Sets the target entity on fire if it's valid and not immune to fire
	 *
	 * @param target the entity we want to set on fire
	 */
	private void setEntityOnFire(@Nullable Entity target) {
		if(EntityUtils.getInstance().isValidEntityLivingBase(target) && !target.isImmuneToFire() && !target.equals(this.ownerEntity)) {
			target.setFire(5);
		}
	}
}
