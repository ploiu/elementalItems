package elementalitems.entities.arrows;

import elementalitems.ElementalTypes;
import elementalitems.util.EntityUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The type Entity fire arrow.
 */
public class EntityFireArrow extends BaseEntityArrow {

	/**
	 * Instantiates a new Entity fire arrow.
	 *
	 * @param worldIn the world in
	 * @param shooter the shooter
	 */
	public EntityFireArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter, ElementalTypes.FIRE);
	}

	/**
	 * Instantiates a new Entity fire arrow.
	 *
	 * @param world the world
	 * @param x     the x
	 * @param y     the y
	 * @param z     the z
	 */
	public EntityFireArrow(World world, double x, double y, double z) {
		super(world, x, y, z, ElementalTypes.FIRE);
	}

	/**
	 * Instantiates a new Entity fire arrow.
	 *
	 * @param world the world
	 */
	public EntityFireArrow(World world) {
		super(world);
		this.type = ElementalTypes.FIRE;
	}

	@Override
	public void applyLandingEffect(BlockPos positionHit, EnumFacing sideHit) {
		// don't do anything if we're in water
		if(!this.inWater) {
			IBlockState fire = Blocks.FIRE.getDefaultState();
			// set the block this is on on fire
			if(fire.getBlock().canPlaceBlockOnSide(this.world, positionHit, sideHit)) {
				BlockPos blockPosForFire = positionHit.offset(sideHit);
				this.world.setBlockState(blockPosForFire, fire, 11);
			}
		}
		super.applyLandingEffect(positionHit, sideHit);
	}

	@Override
	public void applyEffectOnEntity(EntityLivingBase target) {
		// don't apply if we're in water
		if(!this.inWater && EntityUtils.getInstance().isValidEntityLivingBase(target)) {
			target.setFire(10);
		}
	}
}
