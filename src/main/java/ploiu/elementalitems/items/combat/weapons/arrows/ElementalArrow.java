package ploiu.elementalitems.items.combat.weapons.arrows;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.arrow.BaseEntityArrow;
import ploiu.elementalitems.items.ElementalItem;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.util.EntityUtils;

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
		BaseEntityArrow createdArrow = EntityUtils.createArrow(this.type, worldIn, shooter.posX, shooter.posY, shooter.posZ);
		createdArrow.setShooter(shooter);
		return createdArrow;
	}

	@Override
	public ElementalTypes getType() {
		return this.type;
	}
}
