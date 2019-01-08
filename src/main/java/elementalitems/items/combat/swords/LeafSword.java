package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.combat.ISharedLeafCombatEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * The type Leaf sword.
 */
public class LeafSword extends BaseSword implements ISharedLeafCombatEffect {

	/**
	 * Instantiates a new Leaf sword.
	 */
	public LeafSword() {
		super(ElementalTypes.LEAF);
		this.tooltip = TextFormatting.GREEN + "Blade Of Grass!";
	}

	@Override
	protected boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		// no op TODO maybe?
		return true;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player) {
		// no op
	}

	@Override
	protected void spawnAttackParticles(WorldServer worldServer, EntityLivingBase targetToSpawnParticlesAt) {
		// TODO
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
		this.enchantWithSmite(stack, entity);
	}
}
