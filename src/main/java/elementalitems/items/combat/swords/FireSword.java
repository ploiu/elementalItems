package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * The type Fire sword.
 */
public class FireSword extends BaseSword {

	/**
	 * Instantiates a new Fire sword.
	 */
	public FireSword() {
		super(ElementalType.FIRE);
	}

	/**
	 * Instantiates a new Fire sword.
	 *
	 * @param material the material
	 * @param name     the name
	 * @param type     the type
	 */
	public FireSword(Item.ToolMaterial material, String name, ElementalType type) {
		// no op
	}

	@Override
	public boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		// checks for the target
		if(EntityUtils.getInstance().isValidEntityLivingBase(target) && !target.isImmuneToFire()) {
			// set the target on fire
			target.setFire(10);
		}

		return true;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player, EnumHand hand) {

	}

}
