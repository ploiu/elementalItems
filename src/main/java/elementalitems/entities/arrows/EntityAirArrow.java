package elementalitems.entities.arrows;

import elementalitems.ElementalTypes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
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
		super(worldIn, shooter, ElementalTypes.AIR);
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
		super(world, x, y, z, ElementalTypes.AIR);
	}

	/**
	 * Instantiates a new Entity air arrow. This is used during rendering, so ignore your IDE when it
	 * says that it's unused
	 *
	 * @param world the world
	 */
	public EntityAirArrow(World world) {
		super(world);
		this.type = ElementalTypes.AIR;
	}

	@Override
	public void applyEffectOnEntity(EntityLivingBase target) {
		// do extra damage that bypasses armor
		target.attackEntityFrom(DamageSource.MAGIC.setDamageBypassesArmor().setDamageIsAbsolute(), 4.0F);
	}
}
