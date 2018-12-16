package elementalitems.entities.arrows;

import elementalitems.ElementalType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * The type Entity plain arrow.
 */
public class EntityPlainArrow extends BaseEntityArrow {

	/**
	 * Instantiates a new Entity plain arrow.
	 *
	 * @param worldIn the world in
	 * @param shooter the shooter
	 */
	public EntityPlainArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter, ElementalType.PLAIN);
	}

	/**
	 * Instantiates a new Entity plain arrow.
	 *
	 * @param world the world
	 * @param x     the x
	 * @param y     the y
	 * @param z     the z
	 */
	public EntityPlainArrow(World world, double x, double y, double z) {
		super(world, x, y, z, ElementalType.PLAIN);
	}

	/**
	 * Instantiates a new Entity plain arrow.
	 *
	 * @param world the world
	 */
	public EntityPlainArrow(World world) {
		super(world);
		this.type = ElementalType.PLAIN;
	}

	@Override
	public void applyEffectOnEntity(EntityLivingBase target) {
		// no op
	}
}