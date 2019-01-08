package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * The type Plain sword.
 */
public class PlainSword extends BaseSword {

	/**
	 * Instantiates a new Plain sword.
	 */
	public PlainSword() {
		super(ElementalTypes.PLAIN);
		this.tooltip = TextFormatting.GRAY + "The Root Of All!";
	}

	@Override
	protected boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		// no op
		return false;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player) {
		// no op
	}

	@Override
	protected void spawnAttackParticles(WorldServer worldServer, EntityLivingBase targetToSpawnParticlesAt) {
		// no op
	}
}
