package elementalitems.entities.arrows;

import elementalitems.ElementalType;
import elementalitems.sharedeffects.combat.ISharedEarthEffects;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * The type Entity earth arrow.
 */
public class EntityEarthArrow extends BaseEntityArrow implements ISharedEarthEffects {

	/**
	 * Instantiates a new Entity earth arrow.
	 *
	 * @param worldIn the world in
	 * @param shooter the shooter
	 */
	public EntityEarthArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter, ElementalType.EARTH);
	}

	/**
	 * Instantiates a new Entity earth arrow.
	 *
	 * @param world the world
	 * @param x     the x
	 * @param y     the y
	 * @param z     the z
	 */
	public EntityEarthArrow(World world, double x, double y, double z) {
		super(world, x, y, z, ElementalType.EARTH);
	}

	/**
	 * Instantiates a new Entity earth arrow.
	 *
	 * @param world the world
	 */
	public EntityEarthArrow(World world) {
		super(world);
		this.type = ElementalType.EARTH;
	}

	@Override
	public void applyEffectOnEntity(EntityLivingBase target) {
		if(EntityUtils.getInstance().isValidEntityLivingBase(target) && !target.onGround) {
			this.strikeDownEntity(target);
		}
	}

}
