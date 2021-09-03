package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ItemTier.WOOD;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.leafCrystal;

public class LeafArmorMaterial extends BaseArmorMaterial {
	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(leafCrystal);
	}

	@Override
	public String getName() {
		return "elementalitems:leaf";
	}

	@Override
	public int getEnchantmentValue() {
		return WOOD.getEnchantmentValue();
	}
}
