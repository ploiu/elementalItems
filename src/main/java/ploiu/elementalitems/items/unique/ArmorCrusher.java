package ploiu.elementalitems.items.unique;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItem;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.items.tools.itemtiers.TierRegistry;
import ploiu.elementalitems.util.EntityUtils;

import java.util.HashSet;
import java.util.List;

public class ArmorCrusher extends ToolItem implements ElementalItem {


	public ArmorCrusher() {
		super(10, -3.3f, TierRegistry.earthTier, new HashSet<>(), new Properties().group(ItemGroup.TAB_COMBAT).maxDamage(4000));
		this.setRegistryName("warhammer");
		ElementalItemsItemRegistry.addItem(this);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		// damage the target's equipped armor a lot
		List<ItemStack> targetArmor = EntityUtils.getEntityArmor(target);
		targetArmor.forEach(piece -> piece.damageItem((int) this.attackDamage * 10, target, (entity -> { })));
		return super.hitEntity(stack, target, attacker);
	}

	@Override
	public ElementalTypes getType() {
		return ElementalTypes.EARTH;
	}
}
