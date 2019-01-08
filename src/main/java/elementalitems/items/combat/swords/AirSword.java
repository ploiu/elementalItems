package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.combat.ISharedAirCombatEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * The type Air sword.
 */
public class AirSword extends BaseSword implements ISharedAirCombatEffect {

	/**
	 * Instantiates a new Air sword.
	 */
	public AirSword() {
		super(ElementalTypes.AIR);
		this.tooltip = TextFormatting.BLUE + "And We Have Liftoff!";
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

	@Override
	protected void spawnAttackParticles(WorldServer world, EntityLivingBase targetToSpawnParticlesAt) {
		world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, targetToSpawnParticlesAt.posX, targetToSpawnParticlesAt.posY, targetToSpawnParticlesAt.posZ, 50, targetToSpawnParticlesAt.width, targetToSpawnParticlesAt.height, targetToSpawnParticlesAt.width, 0, 0);
	}
}
