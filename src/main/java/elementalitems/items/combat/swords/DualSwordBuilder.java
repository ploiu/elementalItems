package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import elementalitems.items.ElementalMaterials;
import elementalitems.sharedeffects.combat.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DualSwordBuilder implements ISharedFireEffect, ISharedIceEffect, ISharedLeafEffect, ISharedWaterEffect, ISharedEarthEffect, ISharedAirEffect, ISharedEnderEffect {
	private ElementalType type1;
	private ElementalType type2;

	public DualSwordBuilder(ElementalType type1, ElementalType type2) {
		this.type1 = type1;
		this.type2 = type2;
	}

	public DualSword build() {
		return new DualSword(ElementalMaterials.getInstance().TOOL_PLAIN, this.type1, this.type2, this.getActiveEffectFromType(this.type1), this.getActiveEffectFromType(this.type2), this.getPassiveEffectFromType(this.type1), this.getPassiveEffectFromType(this.type2));
	}

	/**
	 * creates an {@link IEffect} based on the passed {@link ElementalType}. This is the effect that will be applied
	 * when the user of this sword hits another entity
	 *
	 * @param type the ElementalType we want to get the effect of
	 * @return an Effect to be used when the user of this sword hits an entity with this sword equipped
	 */
	@Nonnull
	private IEffect getActiveEffectFromType(ElementalType type) {
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
	private IPassiveEffect getPassiveEffectFromType(ElementalType type) {
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
}
