package elementalitems.entities.arrows;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.combat.ISharedEarthCombatEffect;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * The type Entity earth arrow.
 */
public class EntityEarthArrow extends BaseEntityArrow implements ISharedEarthCombatEffect {

	/**
	 * Instantiates a new Entity earth arrow.
	 *
	 * @param worldIn the world in
	 * @param shooter the shooter
	 */
	public EntityEarthArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter, ElementalTypes.EARTH);
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
		super(world, x, y, z, ElementalTypes.EARTH);
	}

	/**
	 * Instantiates a new Entity earth arrow.
	 *
	 * @param world the world
	 */
	public EntityEarthArrow(World world) {
		super(world);
		this.type = ElementalTypes.EARTH;
	}

	@Override
	public void applyEffectOnEntity(EntityLivingBase target) {
		if(EntityUtils.getInstance().isValidEntityLivingBase(target) && !target.onGround) {
			this.strikeDownEntity(target);
		}
	}
}
