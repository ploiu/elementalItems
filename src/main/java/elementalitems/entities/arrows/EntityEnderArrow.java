package elementalitems.entities.arrows;

import elementalitems.ElementalTypes;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

/**
 * This EntityArrow  allows the shooter to teleport to where the arrow lands, be it mob or block
 */
public class EntityEnderArrow extends BaseEntityArrow {

	/**
	 * Instantiates a new Entity ender arrow.
	 *
	 * @param worldIn the world in
	 * @param shooter the shooter
	 */
	public EntityEnderArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter, ElementalTypes.ENDER);
	}

	/**
	 * Instantiates a new Entity ender arrow.
	 *
	 * @param world the world
	 * @param x     the x
	 * @param y     the y
	 * @param z     the z
	 */
	public EntityEnderArrow(World world, double x, double y, double z) {
		super(world, x, y, z, ElementalTypes.ENDER);
	}

	/**
	 * Instantiates a new Entity ender arrow.
	 *
	 * @param world the world
	 */
	public EntityEnderArrow(World world) {
		super(world);
		this.type = ElementalTypes.ENDER;
	}

	@Override
	public void applyEffectOnEntity(EntityLivingBase target) {
		// if there is a shooter, and the target is valid, teleport our shooter to the target
		if(!this.world.isRemote && EntityUtils.getInstance().isValidEntityLivingBase(this.shootingEntity) && EntityUtils.getInstance().isValidEntityLivingBase(target)) {
			// attempt to teleport our shooter, and if successful play teleport sounds
			if(((EntityLivingBase) this.shootingEntity).attemptTeleport(target.getPosition().getX(), target.getPosition().getY(), target.getPosition().getZ())) {
				this.world.playSound(null, this.shootingEntity.getPosition().getX(), this.shootingEntity.getPosition().getY(), this.shootingEntity.getPosition().getZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
				this.shootingEntity.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
			}
		}
	}

	@Override
	public void applyLandingEffect(BlockPos positionHit, EnumFacing sideHit) {
		// if the world is not remote and our shooter is a valid entity, attempt to teleport our shooter to where the arrow landed
		if(!this.world.isRemote && EntityUtils.getInstance().isValidEntityLivingBase(this.shootingEntity)) {
			EnderTeleportEvent event = new EnderTeleportEvent((EntityLivingBase) this.shootingEntity, this.posX, this.posY, this.posZ, 0);
			this.shootingEntity.setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
			// play the sounds
			this.world.playSound(null, this.shootingEntity.getPosition().getX(), this.shootingEntity.getPosition().getY(), this.shootingEntity.getPosition().getZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.shootingEntity.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
		}
	}
}
