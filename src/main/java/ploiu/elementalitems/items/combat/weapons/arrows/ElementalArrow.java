package ploiu.elementalitems.items.combat.weapons.arrows;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;
import ploiu.elementalitems.entity.arrow.BaseEntityArrow;
import ploiu.elementalitems.items.ElementalItem;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.util.EntityUtils;

public class ElementalArrow extends ArrowItem implements ElementalItem {
	private final ElementalTypes type;

	public ElementalArrow(ElementalTypes type) {
		super(new Properties().tab(ItemGroup.TAB_COMBAT));
		this.type = type;
		this.setRegistryName(String.format("arrow_%s", this.type));
		ElementalItemsItemRegistry.addItem(this);
	}

	@Override
	public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {
		return EntityUtils.createArrow(this.type, worldIn, shooter);
	}

	@Override
	public ElementalTypes getType() {
		return this.type;
	}
}
