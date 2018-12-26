package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import elementalitems.sharedeffects.combat.ISharedWaterEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * The type Water sword.
 */
public class WaterSword extends BaseSword implements ISharedWaterEffect {

	/**
	 * Instantiates a new Water sword.
	 */
	public WaterSword() {
		super(ElementalType.WATER);
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
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
		// give this item knockback V
		this.enchantWithKnockBack(stack, entity);
	}
}
