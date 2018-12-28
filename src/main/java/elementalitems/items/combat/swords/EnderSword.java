package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.sharedeffects.combat.ISharedEnderCombatEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
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
		this.tooltip = TextFormatting.DARK_AQUA + "Vwoop!";
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
		this.throwEnderPearl(world, player);
	}
}
