package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * The type Plain sword.
 */
public class PlainSword extends BaseSword {

	/**
	 * Instantiates a new Plain sword.
	 */
	public PlainSword() {
		super(ElementalType.PLAIN);
	}

	@Override
	protected boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		// no op
		return false;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player, EnumHand hand) {
		// no op
	}
}
