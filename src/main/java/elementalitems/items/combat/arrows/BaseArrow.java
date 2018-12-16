package elementalitems.items.combat.arrows;

import elementalitems.ElementalItems;
import elementalitems.ElementalType;
import elementalitems.entities.arrows.*;
import elementalitems.items.ElementalItem;
import elementalitems.items.ItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;


/**
 * The type Base arrow.
 */
public class BaseArrow extends ItemArrow implements ElementalItem {

	/**
	 * The Name.
	 */
	protected String name;
	/**
	 * The Type.
	 */
	protected ElementalType type;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ElementalType getType() {
		return this.type;
	}

	private BaseArrow(ElementalType type, String name) {
		this.type = type;
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.COMBAT);
		ItemHandler.items.add(this);
	}

	@Override
	public EntityArrow createArrow(World world, ItemStack stack, EntityLivingBase shooter) {
		switch(this.type) {
			case FIRE:
				return new EntityFireArrow(world, shooter);
			case WATER:
				return new EntityWaterArrow(world, shooter);
			case EARTH:
				return new EntityEarthArrow(world, shooter);
			case AIR:
				return new EntityAirArrow(world, shooter);
			case ICE:
				return new EntityIceArrow(world, shooter);
			case ENDER:
				return new EntityEnderArrow(world, shooter);
			case LEAF:
				return new EntityLeafArrow(world, shooter);
			case PLAIN:
				return new EntityPlainArrow(world, shooter);
			default:
				ElementalItems.logger.log(Level.FATAL, "Error when creating arrow!\n" + "From item: " + stack.getItem().getRegistryName());
				// we don't want to crash their game, so just print something instead of returning null
				return new EntityPlainArrow(world, shooter);
		}
	}


	/**
	 * Instantiates a new Base arrow.
	 *
	 * @param type the type
	 */
	public BaseArrow(ElementalType type) {
		this(type, "arrow_" + type.getTypeName());
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof BaseArrow && ((BaseArrow) other).getType() == this.type;
	}

}
