package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.combat.ISharedAirCombatEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * The type Air sword.
 */
public class AirSword extends BaseSword implements ISharedAirCombatEffect {

	/**
	 * Instantiates a new Air sword.
	 */
	public AirSword() {
		super(ElementalTypes.AIR);
	}

	@Override
	protected boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		this.launchTarget(user, target);
		return true;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player) {
		// no op
	}
}
