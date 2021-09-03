package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ArmorMaterial.GOLD;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.enderCrystal;

public class EnderArmorMaterial extends BaseArmorMaterial {

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.of(enderCrystal);
	}

	@Override
	public String getName() {
		return "elementalitems:ender";
	}

	@Override
	public int getEnchantmentValue() {
		return GOLD.getEnchantmentValue();
	}
}
