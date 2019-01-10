package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.combat.ISharedIceCombatEffect;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import static net.minecraft.init.Biomes.HELL;
import static net.minecraft.util.text.TextFormatting.AQUA;

/**
 * The type Ice sword.
 */
public class IceSword extends BaseSword implements ISharedIceCombatEffect {

	/**
	 * Instantiates a new Ice sword.
	 */
	public IceSword() {
		super(ElementalTypes.ICE);
		this.tooltip = AQUA + "Slow and Steady!";
	}

	/**
	 * Instantiates a new Ice sword.
	 *
	 * @param material the material
	 * @param name     the name
	 * @param type     the type
	 */
	public IceSword(Item.ToolMaterial material, String name, ElementalTypes type) {
	}

	@Override
	protected boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		this.slowAndWeakenTarget(user, target);
		// if the target is a nether mob, then we should do 1.5x the damage
		if(EntityUtils.getInstance().isMobFromBiome(target, HELL) || target instanceof EntityBlaze) {
			target.attackEntityFrom(DamageSource.causeIndirectMagicDamage(target, user), this.getAttackDamage() * 1.5f);
		}
		return false;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player) {
		this.throwSnowball(world, player);
	}

	@Override
	protected void spawnAttackParticles(WorldServer worldServer, EntityLivingBase targetToSpawnParticlesAt) {
		worldServer.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, true, targetToSpawnParticlesAt.posX, targetToSpawnParticlesAt.posY, targetToSpawnParticlesAt.posZ, 50, targetToSpawnParticlesAt.width, targetToSpawnParticlesAt.height, targetToSpawnParticlesAt.width, 0, 0);
		worldServer.spawnParticle(EnumParticleTypes.SNOWBALL, true, targetToSpawnParticlesAt.posX, targetToSpawnParticlesAt.posY, targetToSpawnParticlesAt.posZ, 50, targetToSpawnParticlesAt.width, targetToSpawnParticlesAt.height, targetToSpawnParticlesAt.width, 0, 0);
		
	}
}
