package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ItemTier.WOOD;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.leafCrystal;

public class LeafArmorMaterial extends BaseArmorMaterial {
	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(leafCrystal);
	}

	@Override
	public String getName() {
		return "material_armor_leaf";
	}

	@Override
	public int getEnchantability() {
		return WOOD.getEnchantability();
	}
}
