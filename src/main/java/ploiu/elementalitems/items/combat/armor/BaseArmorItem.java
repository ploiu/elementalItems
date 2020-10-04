package ploiu.elementalitems.items.combat.armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.items.ItemUtils;

public abstract class BaseArmorItem extends ArmorItem implements BaseArmor {

	private final ElementalTypes type;

	public BaseArmorItem(ElementalTypes type, EquipmentSlotType slot) {
		super(ItemUtils.getArmorMaterialFromType(type), slot, new Properties().group(ItemGroup.COMBAT));
		this.type = type;
		this.setRegistryName(String.format("%s_%s", slot.getName(), type.getTypeName()));
		ElementalItemsItemRegistry.addItem(this);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		this.applyPassiveEffect(stack, world, player);
	}

	@Override
	public ElementalTypes getType() {
		return this.type;
	}
}
