package ploiu.elementalitems.items.combat.armor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import ploiu.elementalitems.items.ElementalItem;

public interface BaseArmor extends ElementalItem {

	/**
	 * Called when the user gets hurt, such as when they get damaged by an attack
	 *
	 * @param stack  the current item stack holding this item
	 * @param world  the world this item's user was damaged in
	 * @param source the source of the damage
	 * @param wearer the wearer of this item
	 */
	void onUserHurt(ItemStack stack, World world, DamageSource source, LivingEntity wearer);

	/**
	 * applies this armor's passive effect on the wearer
	 *
	 * @param stack  the ItemStack holding this item
	 * @param world  the World the wearer exists in
	 * @param wearer the entity wearing this piece of armor
	 */
	void applyPassiveEffect(ItemStack stack, World world, LivingEntity wearer);
}
