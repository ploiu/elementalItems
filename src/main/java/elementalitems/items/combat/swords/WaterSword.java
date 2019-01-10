package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.combat.ISharedWaterCombatEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * The type Water sword.
 */
public class WaterSword extends BaseSword implements ISharedWaterCombatEffect {

	/**
	 * Instantiates a new Water sword.
	 */
	public WaterSword() {
		super(ElementalTypes.WATER);
		this.tooltip = TextFormatting.BLUE + "Swift as a Coursing River!";
	}

	@Override
	protected boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		// no op
		return true;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player) {
		// no op TODO maybe?
	}

	@Override
	protected void spawnAttackParticles(WorldServer worldServer, EntityLivingBase targetToSpawnParticlesAt) {
		worldServer.spawnParticle(EnumParticleTypes.WATER_SPLASH, true, targetToSpawnParticlesAt.posX, targetToSpawnParticlesAt.posY, targetToSpawnParticlesAt.posZ, 100, targetToSpawnParticlesAt.width, targetToSpawnParticlesAt.height, targetToSpawnParticlesAt.width, 0);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
		// give this item knockback V
		this.enchantWithKnockBack(stack, entity);
	}
}
