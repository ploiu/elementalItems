package elementalitems.entities.arrows;

import elementalitems.ElementalType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * The type Entity air arrow.
 */
public class EntityAirArrow extends BaseEntityArrow {

	/**
	 * Instantiates a new Entity air arrow.
	 *
	 * @param worldIn the world in
	 * @param shooter the shooter
	 */
	public EntityAirArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter, ElementalType.AIR);
	}

	/**
	 * Instantiates a new Entity air arrow.
	 *
	 * @param world the world
	 * @param x     the x
	 * @param y     the y
	 * @param z     the z
	 */
	public EntityAirArrow(World world, double x, double y, double z) {
		super(world, x, y, z, ElementalType.AIR);
	}

	/**
	 * Instantiates a new Entity air arrow. This is used during rendering, so ignore your IDE when it
	 * says that it's unused
	 *
	 * @param world the world
	 */
	public EntityAirArrow(World world) {
		super(world);
		this.type = ElementalType.AIR;
	}

	@Override
	public void applyEffectOnEntity(EntityLivingBase target) {
		// TODO
	}
}
