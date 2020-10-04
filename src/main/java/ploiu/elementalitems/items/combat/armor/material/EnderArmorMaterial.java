package ploiu.elementalitems.items.combat.armor.material;

import net.minecraft.item.crafting.Ingredient;

import static net.minecraft.item.ArmorMaterial.GOLD;
import static ploiu.elementalitems.items.ElementalItemsItemRegistry.enderCrystal;

public class EnderArmorMaterial extends BaseArmorMaterial {

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(enderCrystal);
	}

	@Override
	public String getName() {
		return "material_armor_ender";
	}

	@Override
	public int getEnchantability() {
		return GOLD.getEnchantability();
	}
}
