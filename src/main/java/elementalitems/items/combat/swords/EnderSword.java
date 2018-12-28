package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.combat.ISharedEnderCombatEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * The type Ender sword.
 */
public class EnderSword extends BaseSword implements ISharedEnderCombatEffect {

	/**
	 * Instantiates a new Ender sword.
	 */
	public EnderSword() {
		super(ElementalTypes.ENDER);
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
		world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if(!world.isRemote) {
			// we need to spawn an ender pearl into the world
			EntityEnderPearl enderPearl = new EntityEnderPearl(world, player);
			enderPearl.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(enderPearl);
		}
	}
}
