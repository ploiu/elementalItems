package elementalitems.items.combat.armor;

import elementalitems.ElementalType;
import elementalitems.items.ElementalItem;
import elementalitems.items.ItemHandler;
import elementalitems.util.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * The type Base armor.
 */
public abstract class BaseArmor extends ItemArmor implements ElementalItem {

	/**
	 * The Type.
	 */
	protected final ElementalType type;
	/**
	 * The Name.
	 */
	protected final String name;


	private BaseArmor(ItemArmor.ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlot, ElementalType type, String name) {
		super(materialIn, -1, equipmentSlot);
		this.type = type;
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.COMBAT);
		ItemHandler.items.add(this);
	}

	/**
	 * Instantiates a new Base armor.
	 *
	 * @param type          the type
	 * @param equipmentSlot the equipment slot
	 */
	public BaseArmor(ElementalType type, EntityEquipmentSlot equipmentSlot) {
		this(Utils.getInstance().getArmorMaterialFromElementalType(type), equipmentSlot, type, equipmentSlot.getName() + "_" + type.getTypeName());
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ElementalType getType() {
		return this.type;
	}

	/**
	 * called when an {@link EntityLivingBase} wearing a piece of BaseArmor is hurt
	 *
	 * @param damageSource the source of the damage
	 * @param wearer       the EntityLivingBase wearing the armor
	 */
	public abstract void onUserHurt(DamageSource damageSource, EntityLivingBase wearer);

	/**
	 * applies a passive effect to the entity wearing this armor
	 *
	 * @param wearer the wearer of the armor
	 */
	public abstract void applyPassiveEffect(EntityLivingBase wearer);

	/**
	 * Gets number of equipped armor pieces of this elemental type.
	 *
	 * @param wearer the wearer
	 * @return the number of equipped armor pieces of this elemental type
	 */
	protected int getNumberOfEquippedArmorPiecesOfThisElementalType(@Nonnull EntityLivingBase wearer) {
		int numberOfPieces = 0;
		Iterable<ItemStack> wearerArmorList = wearer.getArmorInventoryList();
		for(ItemStack armorStack : wearerArmorList) {
			Item armorItem = armorStack.getItem();
			// if armorItem is a BaseArmor
			if(armorItem instanceof BaseArmor && ((BaseArmor) armorItem).getType() == this.type) {
				numberOfPieces++;
			}
		}
		return numberOfPieces;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		this.applyPassiveEffect(player);
	}

	@Override
	public boolean equals(Object other) {
		if(!(other instanceof BaseArmor)) {
			return false;
		}
		return ((BaseArmor) other).getType() == this.getType() && ((BaseArmor) other).getEquipmentSlot() == this.getEquipmentSlot();
	}

	@Override
	public String toString() {
		return "BaseArmor{" +
				       "type=" + this.type +
				       ", name='" + this.name + '\'' +
				       ", armorType=" + this.armorType.getName() +
				       '}';
	}
}
