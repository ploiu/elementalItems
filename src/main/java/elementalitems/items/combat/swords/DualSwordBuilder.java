package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.items.combat.swords.dualEffects.IEffect;
import elementalitems.items.combat.swords.dualEffects.IPassiveEffect;
import elementalitems.items.combat.swords.dualEffects.IRightClickEffect;
import elementalitems.sharedeffects.combat.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

public class DualSwordBuilder implements ISharedFireCombatEffect, ISharedIceCombatEffect, ISharedLeafCombatEffect, ISharedWaterCombatEffect, ISharedEarthCombatEffect, ISharedAirCombatEffect, ISharedEnderCombatEffect {
	private ElementalTypes type1;
	private ElementalTypes type2;

	public DualSwordBuilder(ElementalTypes type1, ElementalTypes type2) {
		this.type1 = type1;
		this.type2 = type2;
	}

	public DualSword build() {
		// sort our types before using them
		ElementalTypes[] sortedTypes = new ElementalTypes[]{this.type1, this.type2};
		Arrays.sort(sortedTypes);
		return new DualSword(
				sortedTypes[0],
				sortedTypes[1],
				this.getActiveEffectFromType(sortedTypes[0]),
				this.getActiveEffectFromType(sortedTypes[1]),
				this.getPassiveEffectFromType(sortedTypes[0]),
				this.getPassiveEffectFromType(sortedTypes[1])
		).setRightClickEffect(this.getRightClickEffectFromType(sortedTypes[this.getRightClickEffectFromType(sortedTypes[0]) == null ? 1 : 0]));
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
				return (user, target) -> this.ignite(target);
			case WATER:
				return (user, target) -> {
					// no op
				};
			case EARTH:
				return (user, target) -> {
					if(target.onGround) {
						this.buryEntity(target);
					} else {
						this.strikeDownEntity(target);
					}
				};
			case AIR:
				return this::launchTarget;
			case ICE:
				return this::slowAndWeakenTarget;
			case ENDER:
				return (user, target) -> this.teleportEntity(target);
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
				return this::enchantWithKnockBack;
			case EARTH:
				return null;
			case AIR:
				return null;
			case ICE:
				return null;
			case ENDER:
				return null;
			case LEAF:
				return this::enchantWithSmite;
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
				return this::throwSnowball;
			case WATER:
				return null;
			case LEAF:
				return null;
			case EARTH:
				return null;
			case AIR:
				return null;
			case ENDER:
				return this::throwEnderPearl;
		}
		throw new IllegalArgumentException(type + " did not match the set choices for creating a dual sword!");
	}
}
