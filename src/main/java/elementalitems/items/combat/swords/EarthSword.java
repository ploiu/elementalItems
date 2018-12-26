package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import elementalitems.sharedeffects.combat.ISharedEarthEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * The type Earth sword.
 */
public class EarthSword extends BaseSword implements ISharedEarthEffect {

	/**
	 * Instantiates a new Earth sword.
	 */
	public EarthSword() {
		super(ElementalType.EARTH);
	}

	@Override
	public boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		// check if it's on the ground
		if(target.onGround) {
			this.buryEntity(target);
		} else {
			this.strikeDownEntity(target);
		}

		return true;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player) {

	}
}
