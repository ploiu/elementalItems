package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import elementalitems.sharedeffects.combat.ISharedFireEffect;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * The type Fire sword.
 */
public class FireSword extends BaseSword implements ISharedFireEffect {

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
		this.ignite(target);
		return true;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player) {

	}

}
