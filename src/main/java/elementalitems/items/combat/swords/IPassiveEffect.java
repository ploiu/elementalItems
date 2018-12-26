package elementalitems.items.combat.swords;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

@FunctionalInterface
public interface IPassiveEffect {
	void apply(ItemStack thisItem, Entity owner);
}
