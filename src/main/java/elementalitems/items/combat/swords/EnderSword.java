package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.combat.ISharedEnderCombatEffect;
import elementalitems.util.ElementalUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Map;

/**
 * The type Ender sword.
 */
public class EnderSword extends BaseSword implements ISharedEnderCombatEffect {

	/**
	 * Instantiates a new Ender sword.
	 */
	public EnderSword() {
		super(ElementalTypes.ENDER);
		this.tooltip = TextFormatting.DARK_AQUA + "Vwoop!";
	}

	/**
	 * Instantiates a new Ender sword.
	 *
	 * @param material the material
	 * @param name     the name
	 * @param type     the type
	 */
	public EnderSword(Item.ToolMaterial material, String name, ElementalTypes type) {
	}

	@Override
	protected boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		this.teleportEntity(target);
		return true;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player) {
		this.throwEnderPearl(world, player);
	}

	@Override
	protected void spawnAttackParticles(WorldServer worldServer, EntityLivingBase targetToSpawnParticlesAt) {
		Map<EnumParticleTypes, Integer> particlesToSpawn = ElementalUtils.getInstance().getParticlesForElementalType(this.type);
		// spawn all the particle types associated with this
		particlesToSpawn.forEach((type, count) -> {
			// use the worldServer to spawn the particles
			worldServer.spawnParticle(type, false, targetToSpawnParticlesAt.posX, targetToSpawnParticlesAt.posY, targetToSpawnParticlesAt.posZ, count, targetToSpawnParticlesAt.width, targetToSpawnParticlesAt.height, targetToSpawnParticlesAt.width, targetToSpawnParticlesAt.getRNG().nextGaussian());
		});
	}
}
