package ploiu.elementalitems.items.combat.weapons.swords.dual;

import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.combat.ElementalEffects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

public class DualSwordBuilder {
	private ElementalTypes type1;
	private ElementalTypes type2;

	private String tooltip = null;

	public DualSwordBuilder(ElementalTypes type1, ElementalTypes type2) {
		this.type1 = type1;
		this.type2 = type2;
	}

	public DualSwordBuilder tooltip(String tooltip) {
		this.tooltip = tooltip;
		return this;
	}

	public DualSword build() {
		// sort our types before using them
		ElementalTypes[] sortedTypes = new ElementalTypes[]{this.type1, this.type2};
		Arrays.sort(sortedTypes);
		DualSword builtSword = new DualSword(
				sortedTypes[0],
				sortedTypes[1],
				this.getActiveEffectFromType(sortedTypes[0]),
				this.getActiveEffectFromType(sortedTypes[1]),
				this.getPassiveEffectFromType(sortedTypes[0]),
				this.getPassiveEffectFromType(sortedTypes[1])
		).setRightClickEffect(this.getRightClickEffectFromType(sortedTypes[this.getRightClickEffectFromType(sortedTypes[0]) == null ? 1 : 0]));
		if(this.tooltip != null) {
			// TODO set a tooltip
		}
		return builtSword;
	}

	/**
	 * creates an {@link IEffect} based on the passed {@link ElementalTypes}. This is the effect that will be applied
	 * when the user of this sword hits another entity
	 *
	 * @param type the ElementalTypes we want to get the effect of
	 * @return an Effect to be used when the user of this sword hits an entity with this sword equipped
	 */
	@Nonnull
	private IEffect getActiveEffectFromType(ElementalTypes type) {
		switch(type) {
			case FIRE:
				return (user, target) -> ElementalEffects.ignite(target);
			case WATER:
				return (user, target) -> {
					// no op
				};
			case EARTH:
				return (user, target) -> {
					if(target.getEntityWorld().getBlockState(target.getPosition().down(1)).isSolid()) {
						ElementalEffects.bury(target);
					} else {
						ElementalEffects.strikeDown(target);
					}
				};
			case AIR:
				return ElementalEffects::launchTarget;
			case ICE:
				return (user, target) -> ElementalEffects.slowAndWeakenTarget(target);
			case ENDER:
				return (user, target) -> ElementalEffects.teleportTarget(target);
			case LEAF:
				return (user, target) -> {
					// no op
				};
			case PLAIN:
				return (user, target) -> {
					// no op	
				};
		}
		throw new IllegalArgumentException(type + " did not match the set choices for creating a dual sword!");
	}

	@Nullable
	private IPassiveEffect getPassiveEffectFromType(ElementalTypes type) {
		switch(type) {
			case FIRE:
				return null;
			case WATER:
				return (thisItem, owner) -> ElementalEffects.enchantWithKnockback(thisItem);
			case EARTH:
				return null;
			case AIR:
				return null;
			case ICE:
				return null;
			case ENDER:
				return null;
			case LEAF:
				return ElementalEffects::enchantWithSmite;
			case PLAIN:
				return null;
		}
		throw new IllegalArgumentException(type + " did not match the set choices for creating a dual sword!");
	}

	@Nullable
	private IRightClickEffect getRightClickEffectFromType(ElementalTypes type) {
		switch(type) {
			case PLAIN:
				return null;
			case FIRE:
				return null;
			case ICE:
				return ElementalEffects::throwSnowball;
			case WATER:
				return null;
			case LEAF:
				return null;
			case EARTH:
				return null;
			case AIR:
				return null;
			case ENDER:
				return ElementalEffects::throwEnderpearl;
		}
		throw new IllegalArgumentException(type + " did not match the set choices for creating a dual sword!");
	}
}