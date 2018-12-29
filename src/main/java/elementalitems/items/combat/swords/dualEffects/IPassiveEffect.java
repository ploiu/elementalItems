package elementalitems.items.combat.swords.dualEffects;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

@FunctionalInterface
public interface IPassiveEffect {
	void apply(ItemStack thisItem, Entity owner);
}
