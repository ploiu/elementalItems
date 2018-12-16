package elementalitems.entities.arrows;

import elementalitems.ElementalType;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.init.Biomes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * The type Entity water arrow.
 */
public class EntityWaterArrow extends BaseEntityArrow {

	/**
	 * Instantiates a new Entity water arrow.
	 *
	 * @param worldIn the world in
	 * @param shooter the shooter
	 */
	public EntityWaterArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter, ElementalType.WATER);
	}

	@Override
	public boolean handleWaterMovement() {
		return false;
	}

	/**
	 * Instantiates a new Entity water arrow.
	 *
	 * @param world the world
	 * @param x     the x
	 * @param y     the y
	 * @param z     the z
	 */
	public EntityWaterArrow(World world, double x, double y, double z) {
		super(world, x, y, z, ElementalType.WATER);
	}

	/**
	 * Instantiates a new Entity water arrow.
	 *
	 * @param world the world
	 */
	public EntityWaterArrow(World world) {
		super(world);
		this.type = ElementalType.WATER;
	}

	@Override
	public void applyEffectOnEntity(EntityLivingBase target) {
		// deal more damage if the arrow is wet
		if(EntityUtils.getInstance().isValidEntityLivingBase(target)) {
			// extinguish the entity and deal extra damage if it's from the nether or is a blaze
			target.extinguish();
			if(EntityUtils.getInstance().isMobFromBiome(target, Biomes.HELL) || target instanceof EntityBlaze) {
				target.attackEntityFrom(DamageSource.causeArrowDamage(this, this.shootingEntity), 2.0f);
			}
		}
	}
}
