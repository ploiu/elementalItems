package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * The type Water sword.
 */
public class WaterSword extends BaseSword {

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
	protected void specialEffect(World world, EntityPlayer player, EnumHand hand) {
		// no op TODO maybe?
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
		// if it's not enchanted yet we must enchant it with knockback V
		if(!stack.isItemEnchanted()) {
			// TODO based on player level
			stack.addEnchantment(Enchantments.KNOCKBACK, 5);
		}

	}

}
