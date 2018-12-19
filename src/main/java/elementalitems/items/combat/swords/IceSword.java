package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import static net.minecraft.init.Biomes.HELL;

/**
 * The type Ice sword.
 */
public class IceSword extends BaseSword {

	/**
	 * Instantiates a new Ice sword.
	 */
	public IceSword() {
		super(ElementalType.ICE);
	}

	/**
	 * Instantiates a new Ice sword.
	 *
	 * @param material the material
	 * @param name     the name
	 * @param type     the type
	 */
	public IceSword(Item.ToolMaterial material, String name, ElementalType type) {
	}

	@Override
	protected boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		// check if the entity is alive and can be interacted with
		if(EntityUtils.getInstance().isValidEntityLivingBase(target)) {
			// afflict the target with weakness and slowness for 5 seconds
			target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 2, false, true));
			target.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 100, 2, false, true));
			// if the target is a nether mob, then we should do 1.5x the damage
			if(EntityUtils.getInstance().isMobFromBiome(target, HELL) || target instanceof EntityBlaze) {
				target.attackEntityFrom(DamageSource.causeIndirectMagicDamage(target, user), this.getAttackDamage() * 1.5f);
			}

		}

		return false;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player) {
		world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if(!world.isRemote) {
			EntitySnowball entitysnowball = new EntitySnowball(world, player);
			entitysnowball.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(entitysnowball);
		}

	}

}
