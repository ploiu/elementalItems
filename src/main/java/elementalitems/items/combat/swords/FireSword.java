package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.combat.ISharedFireCombatEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * The type Fire sword.
 */
public class FireSword extends BaseSword implements ISharedFireCombatEffect {

	/**
	 * Instantiates a new Fire sword.
	 */
	public FireSword() {
		super(ElementalTypes.FIRE);
	}

	/**
	 * Instantiates a new Fire sword.
	 *
	 * @param material the material
	 * @param name     the name
	 * @param type     the type
	 */
	public FireSword(Item.ToolMaterial material, String name, ElementalTypes type) {
		// no op
	}

	@Override
	public boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		this.ignite(target);
		return true;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player) {

	}

}
