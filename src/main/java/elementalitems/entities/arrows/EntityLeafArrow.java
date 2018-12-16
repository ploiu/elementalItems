package elementalitems.entities.arrows;

import elementalitems.ElementalType;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * The type Entity leaf arrow.
 */
public class EntityLeafArrow extends BaseEntityArrow {

	/**
	 * Instantiates a new Entity leaf arrow.
	 *
	 * @param worldIn the world in
	 * @param shooter the shooter
	 */
	public EntityLeafArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter, ElementalType.LEAF);
	}

	/**
	 * Instantiates a new Entity leaf arrow.
	 *
	 * @param world the world
	 */
	public EntityLeafArrow(World world) {
		super(world);
		this.type = ElementalType.LEAF;
	}

	/**
	 * Instantiates a new Entity leaf arrow.
	 *
	 * @param world the world
	 * @param x     the x
	 * @param y     the y
	 * @param z     the z
	 */
	public EntityLeafArrow(World world, double x, double y, double z) {
		super(world, x, y, z, ElementalType.LEAF);
	}

	@Override
	public void applyEffectOnEntity(EntityLivingBase target) {
		// check if the target is valid and is undead
		if(EntityUtils.getInstance().isValidEntityLivingBase(target) && target.isEntityUndead()) {
			// deal double damage to the target
			target.attackEntityFrom(DamageSource.MAGIC, 4.0f);
		}
	}
}
