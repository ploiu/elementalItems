package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import elementalitems.sharedeffects.combat.ISharedEarthEffects;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * The type Earth sword.
 */
public class EarthSword extends BaseSword implements ISharedEarthEffects {

	/**
	 * Instantiates a new Earth sword.
	 */
	public EarthSword() {
		super(ElementalType.EARTH);
	}

	@Override
	public boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		// make sure the target is alive and not null
		if(EntityUtils.getInstance().isValidEntityLivingBase(target)) {
			// check if it's on the ground
			if(target.onGround) {
				this.buryEntity(target);
			} else {
				this.strikeDownEntity(target);
			}
		}

		return true;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player, EnumHand hand) {

	}

	/**
	 * takes an EntityLivingBase and pushes it under ground so it's in above its head
	 *
	 * @param target the entity we are attacking
	 */
	private void buryEntity(EntityLivingBase target) {
		// get its height
		float height = target.height + 1.0f;
		// send it height blocks under ground
		target.setEntityBoundingBox(target.getEntityBoundingBox().offset(0, -height, 0));
		target.resetPositionToBB();
	}

}
