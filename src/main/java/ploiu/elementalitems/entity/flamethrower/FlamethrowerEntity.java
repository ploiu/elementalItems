package ploiu.elementalitems.entity.flamethrower;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;
import ploiu.elementalitems.util.EntityUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FlamethrowerEntity extends ThrowableEntity {

	protected LivingEntity ownerEntity;
	private int ticksExisted = 0;

	public FlamethrowerEntity(LivingEntity livingEntityIn, World worldIn) {
		super(ElementalItemsEntityRegistry.flamethrowerEntity, livingEntityIn, worldIn);
		this.ownerEntity = livingEntityIn;
		this.setNoGravity(true);
		this.setOwner(this.ownerEntity);
	}

	public FlamethrowerEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
		super(type, worldIn);
		this.ownerEntity = null;
		this.setNoGravity(true);
		this.setOwner(this.ownerEntity);
	}

	/**
	 * checks our config file, and if it's set to allow this entity to set blocks on fire, set the block this entity hit on fire
	 *
	 * @param result the {@link RayTraceResult} that has all the information we need
	 */
	private void setHitBlockOnFireIfConfigAllowsIt(BlockRayTraceResult result) {
		// if(ElementalItemsConfig.shouldFlamethrowerSetBlocksOnFire) { TODO
		BlockPos positionHit = result.getBlockPos();
		BlockState fire = Blocks.FIRE.defaultBlockState();
		// set the block this is on on fire
		if(this.level.getBlockState(positionHit).getMaterial().isFlammable() && (!this.level.getBlockState(positionHit.offset(result.getDirection().getNormal())).getMaterial().isLiquid())) {
			BlockPos blockPosForFire = positionHit.offset(result.getDirection().getNormal());
			this.level.setBlock(blockPosForFire, fire, 11);
		}
		// }
	}

	/**
	 * checks if the block we hit blocks movement, and returns the result of that check
	 *
	 * @param result the {@link RayTraceResult} that has all the information we need
	 * @return true if we can not pass through the block, false otherwise
	 */
	private boolean canNotPassThroughHitBlock(BlockRayTraceResult result) {
		// get the block at the result's block position
		BlockPos hitBlockPos = result.getBlockPos();
		BlockState hitState = this.level.getBlockState(hitBlockPos);
		return hitState.getMaterial().blocksMotion();
	}

	/**
	 * attacks the passed {@link LivingEntity} that we collided with, and sets our ownerEntity to be the one who attacked it
	 *
	 * @param hitEntity the entity we collided with
	 */
	private void attackEntityOnDirectHit(@Nonnull LivingEntity hitEntity) {
		// set it on fire first to modify the drops
		hitEntity.setSecondsOnFire(10);
		hitEntity.hurt(DamageSource.IN_FIRE, 10.0f);
		// now make the entity mad at the user
		if(this.ownerEntity != null) {
			hitEntity.setLastHurtByMob(this.ownerEntity);
		}
	}

	@Override
	public boolean shouldRenderAtSqrDistance(double p_70112_1_) {
		return false;
	}

	@Override
	public void tick() {
		super.tick();
		this.ticksExisted++;
		// kill this entity if it's existed for more than 20 ticks
		if(this.ticksExisted >= 20 || this.isInWater()) {
			this.remove();
			return;
		}
		// spawn fire particles after a little bit
		if(this.ticksExisted >= 2 && this.level instanceof ServerWorld) {
			ServerWorld worldServer = (ServerWorld) this.level;
			worldServer.addParticle(ParticleTypes.FLAME, true, this.getX(), this.getY(), this.getZ(), 3.0d, this.getBbWidth() / 4, 0.0d);
			// grow our bounding box, capture any entities that aren't this and aren't our owner in it, and set them on fire
			AxisAlignedBB expandedBoundingBox = this.getBoundingBox().inflate(1, 1, 1);
			worldServer.getEntities(this.ownerEntity, expandedBoundingBox, input -> !this.equals(input)).forEach(this::setEntityOnFire);
		}
	}

	public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
		super.shoot(rotationPitchIn, rotationYawIn, pitchOffset, velocity, inaccuracy);
		if(EntityUtils.isValidLivingEntity(entityThrower)) {
			this.setOwner(entityThrower);
			this.ownerEntity = (LivingEntity) entityThrower;
		}
	}

	@Override
	protected void defineSynchedData() {

	}

	@Override
	public boolean isInvisible() {
		return true;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.5f, 0.5f, true);
	}

	
	
	@Override
	protected void onHit(RayTraceResult result) {
		// this doesn't work if the world isn't a ServerWorld since on the client side, this.thrower is null
		if(this.level.isClientSide()) {
			// set any impacted mobs on fire, and any impacted blocks on fire
			if(result.getType() == RayTraceResult.Type.ENTITY && EntityUtils.isValidLivingEntity(((EntityRayTraceResult) result).getEntity()) && !((EntityRayTraceResult) result).getEntity().equals(this.ownerEntity)) {
				this.attackEntityOnDirectHit((LivingEntity) ((EntityRayTraceResult) result).getEntity());
			} else if(result.getType() == RayTraceResult.Type.BLOCK && result instanceof BlockRayTraceResult) {
				this.setHitBlockOnFireIfConfigAllowsIt((BlockRayTraceResult) result);
				// if the block is not passable, kill this entity after spawning a bunch of particles
				if(this.canNotPassThroughHitBlock((BlockRayTraceResult) result)) {
					// spawn a bunch of particles
					if(this.level instanceof ServerWorld) {
						ServerWorld worldServer = (ServerWorld) this.level;
						worldServer.addParticle(ParticleTypes.FLAME, true, this.getX(), this.getY(), this.getZ(), 3.0d, this.getBbWidth() / 4, 0.0d);
					}
					this.remove();
				}
			}
		}
	}

	/**
	 * Sets the target entity on fire if it's valid and not immune to fire
	 *
	 * @param target the entity we want to set on fire
	 */
	private void setEntityOnFire(@Nullable Entity target) {
		if(EntityUtils.isValidLivingEntity(target) && !target.equals(this.ownerEntity)) {
			target.setSecondsOnFire(5);
		}
	}
}