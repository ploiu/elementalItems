package elementalitems.entities.arrows;

import elementalitems.ElementalType;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * The type Entity ice arrow.
 */
public class EntityIceArrow extends BaseEntityArrow {

	/**
	 * Instantiates a new Entity ice arrow.
	 *
	 * @param world the world
	 * @param x     the x
	 * @param y     the y
	 * @param z     the z
	 */
	public EntityIceArrow(World world, double x, double y, double z) {
		super(world, x, y, z, ElementalType.ICE);
	}

	/**
	 * Instantiates a new Entity ice arrow.
	 *
	 * @param worldIn the world in
	 * @param shooter the shooter
	 */
	public EntityIceArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter, ElementalType.ICE);
	}

	/**
	 * Instantiates a new Entity ice arrow.
	 *
	 * @param world the world
	 */
	public EntityIceArrow(World world) {
		super(world);
		this.type = ElementalType.ICE;
	}

	@Override
	public void applyEffectOnEntity(EntityLivingBase target) {
		// TODO make it freeze surface water too
		if(EntityUtils.getInstance().isValidEntityLivingBase(target)) {
			// slow and weaken the target
			target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 2, false, true));
			target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 2, false, true));
		}
	}
}
