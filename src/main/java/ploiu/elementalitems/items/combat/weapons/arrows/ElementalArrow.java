package ploiu.elementalitems.items.combat.weapons.arrows;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItem;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;

public class ElementalArrow extends ArrowItem implements ElementalItem {
	private final ElementalTypes type;

	public ElementalArrow(ElementalTypes type) {
		super(new Properties().group(ItemGroup.COMBAT));
		this.type = type;
		this.setRegistryName(String.format("arrow_%s", this.type));
		ElementalItemsItemRegistry.addItem(this);
	}

	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		// TODO create proper arrow entity based off of type
		return super.createArrow(worldIn, stack, shooter);
	}

	@Override
	public ElementalTypes getType() {
		return this.type;
	}
}
