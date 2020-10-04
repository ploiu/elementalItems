package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.items.ItemUtils;
import ploiu.elementalitems.items.combat.weapons.BaseWeapon;

public abstract class BaseSword extends SwordItem implements BaseWeapon {

	private final ElementalTypes type;

	public BaseSword(ElementalTypes type) {
		super(ItemUtils.getItemTierFromType(type), 5, -2.4F, new Properties().group(ItemGroup.COMBAT));
		this.type = type;
		this.setRegistryName(String.format("sword_%s", type));
		ElementalItemsItemRegistry.addItem(this);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		this.applyEffect(stack, target, attacker);
		return super.hitEntity(stack, target, attacker);
	}

	@Override
	public ElementalTypes getType() {
		return this.type;
	}
}
