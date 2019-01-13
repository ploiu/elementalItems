package elementalitems.entities.flamethrower;

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

	protected Entity ownerEntity;

	public FlamethrowerEntity(World worldIn) {
		super(worldIn);
	}

	public FlamethrowerEntity(World world, @Nonnull EntityPlayer ownerEntity) {
		super(world, ownerEntity.posX, ownerEntity.posY + ownerEntity.getEyeHeight() - 0.10000000149011612D, ownerEntity.posZ);
		this.setSize(0.5f, 0.5f);
		this.setInvisible(true);
		this.setNoGravity(true);
		this.ownerEntity = ownerEntity;
	}

	public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
		super.shoot(entityThrower, rotationPitchIn, rotationYawIn, pitchOffset, velocity, inaccuracy);
		this.ownerEntity = entityThrower;
		this.isImmuneToFire = true;
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		// set any impacted mobs on fire, and any impacted blocks on fire
		if(result.typeOfHit == RayTraceResult.Type.ENTITY && EntityUtils.getInstance().isValidEntityLivingBase(result.entityHit) && !result.entityHit.equals(this.ownerEntity)) {
			EntityLivingBase hitEntity = (EntityLivingBase) result.entityHit;
			// do some flat damage before setting it on fire
			hitEntity.attackEntityFrom(DamageSource.IN_FIRE, 10.0f);
			hitEntity.setFire(5);
		} else if(result.typeOfHit == RayTraceResult.Type.BLOCK) {
			BlockPos positionHit = result.getBlockPos();
			IBlockState fire = Blocks.FIRE.getDefaultState();
			// set the block this is on on fire
			if(fire.getBlock().canPlaceBlockOnSide(this.world, positionHit, result.sideHit) && (!this.world.getBlockState(positionHit.offset(result.sideHit)).getMaterial().isLiquid())) {
				BlockPos blockPosForFire = positionHit.offset(result.sideHit);
				this.world.setBlockState(blockPosForFire, fire, 11);
			}
			this.setDead();
		}
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		// kill this entity if it's existed for more than 30 ticks
		if(this.ticksExisted >= 15 || this.inWater) {
			this.setDead();
			return;
		}
		// spawn fire particles
		if(this.world instanceof WorldServer) {
			WorldServer worldServer = (WorldServer) this.world;
			worldServer.spawnParticle(EnumParticleTypes.FLAME, true, this.posX, this.posY, this.posZ, 3, this.width / 4, 0, 0, 0.0d, 0);
		}
		// grow our bounding box, capture any entities that aren't this and aren't our owner in it, and set them on fire
		AxisAlignedBB expandedBoundingBox = this.getEntityBoundingBox().grow(1, 1, 1);
		this.world.getEntitiesInAABBexcluding(this.ownerEntity, expandedBoundingBox, null).forEach(this::setEntityOnFire);
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
