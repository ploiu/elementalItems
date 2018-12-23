package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * The type Leaf sword.
 */
public class LeafSword extends BaseSword {

	/**
	 * Instantiates a new Leaf sword.
	 */
	public LeafSword() {
		super(ElementalType.LEAF);
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
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
		// enchant it with smite V if not enchanted
		if(!stack.isItemEnchanted()) {
			stack.addEnchantment(Enchantments.SMITE, 5);
		}

	}

}
