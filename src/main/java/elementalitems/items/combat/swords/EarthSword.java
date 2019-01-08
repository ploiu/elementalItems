package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.combat.ISharedEarthCombatEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * The type Earth sword.
 */
public class EarthSword extends BaseSword implements ISharedEarthCombatEffect {

	/**
	 * Instantiates a new Earth sword.
	 */
	public EarthSword() {
		super(ElementalTypes.EARTH);
		this.tooltip = TextFormatting.DARK_RED + "Taphophobia!";
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

	@Override
	protected void spawnAttackParticles(WorldServer worldServer, EntityLivingBase targetToSpawnParticlesAt) {
		worldServer.spawnParticle(EnumParticleTypes.TOWN_AURA, true, targetToSpawnParticlesAt.posX, targetToSpawnParticlesAt.posY, targetToSpawnParticlesAt.posZ, 200, targetToSpawnParticlesAt.width, targetToSpawnParticlesAt.height, targetToSpawnParticlesAt.width, 0, 0);
	}
}
