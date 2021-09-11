package ploiu.elementalitems.items.combat.weapons.swords.dual;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.combat.weapons.swords.BaseSword;
import ploiu.elementalitems.items.tools.itemtiers.TierRegistry;
import ploiu.elementalitems.util.ElementalUtils;
import ploiu.elementalitems.util.EntityUtils;
import ploiu.elementalitems.util.ItemUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class DualSword extends BaseSword {

	// the types associated with this sword
	private final ElementalTypes type1;
	private final ElementalTypes type2;
	// the effects that will be used on apply
	private IEffect firstEffect;
	private IEffect secondEffect;
	// the effects to be called in this.onUpdate
	private IPassiveEffect firstPassiveEffect;
	private IPassiveEffect secondPassiveEffect;
	// the right click effect for this sword
	private IRightClickEffect rightClickEffect = null;

	public DualSword(ElementalTypes type1, ElementalTypes type2, @Nonnull IEffect firstEffect, @Nonnull IEffect secondEffect) {
		this(type1, type2, firstEffect, secondEffect, null, null);
	}

	public DualSword(ElementalTypes type1, ElementalTypes type2, @Nonnull IEffect firstEffect, @Nonnull IEffect secondEffect, @Nullable IPassiveEffect firstPassiveEffect, @Nullable IPassiveEffect secondPassiveEffect) {
		super(TierRegistry.getDualTierForTypes(type1, type2), "sword_" + type1.getTypeName() + "_" + type2.getTypeName(), ElementalTypes.PLAIN);
		this.type1 = type1;
		this.type2 = type2;
		this.firstEffect = firstEffect;
		this.secondEffect = secondEffect;
		this.firstPassiveEffect = firstPassiveEffect;
		this.secondPassiveEffect = secondPassiveEffect;
	}

	public DualSword setFirstEffect(IEffect firstEffect) {
		this.firstEffect = firstEffect;
		return this;
	}

	public DualSword setSecondEffect(IEffect secondEffect) {
		this.secondEffect = secondEffect;
		return this;
	}

	public DualSword setFirstPassiveEffect(IPassiveEffect firstPassiveEffect) {
		this.firstPassiveEffect = firstPassiveEffect;
		return this;
	}

	public DualSword setSecondPassiveEffect(IPassiveEffect secondPassiveEffect) {
		this.secondPassiveEffect = secondPassiveEffect;
		return this;
	}

	public DualSword setRightClickEffect(IRightClickEffect rightClickEffect) {
		this.rightClickEffect = rightClickEffect;
		return this;
	}


	@Override
	public void applyEffect(ItemStack stack, LivingEntity target, LivingEntity user) {
		if(EntityUtils.isValidLivingEntity(user) && EntityUtils.isValidLivingEntity(target)) {
			// apply both of our effects
			this.firstEffect.apply(user, target);
			this.secondEffect.apply(user, target);
		}
	}

	@Override
	public boolean isValidRepairItem(ItemStack thisItem, ItemStack repairMaterial) {
		return super.isValidRepairItem(thisItem, repairMaterial) || repairMaterial.getItem().equals(ItemUtils.getCrystalForElementalType(this.type1)) || repairMaterial.getItem().equals(ItemUtils.getCrystalForElementalType(this.type2));
	}

	@Override
	public String toString() {
		return "DualSword{" +
				       "firstEffect=" + this.firstEffect +
				       ", secondEffect=" + this.secondEffect +
				       ", type1=" + this.type1 +
				       ", type2=" + this.type2 +
				       ", name='" + this.name + '\'' +
				       '}';
	}

	@Override
	protected void onUpdate(ItemStack stack, World world, Entity owner, int itemSlot, boolean isSelected) {
		if(EntityUtils.isValidLivingEntity(owner)) {
			// make sure our first effect isn't null before trying to apply
			if(this.firstPassiveEffect != null) {
				this.firstPassiveEffect.apply(stack, owner);
			}
			// make sure our second passive effect isn't null before trying to apply
			if(this.secondPassiveEffect != null) {
				this.secondPassiveEffect.apply(stack, owner);
			}
		}
	}

	@Override
	protected void spawnAttackParticles(ServerWorld worldServer, LivingEntity targetToSpawnParticlesAt) {
		// the map of our particles
		Map<IParticleData, Integer> particles = ElementalUtils.getMixedParticlesForElementalTypes(this.type1, this.type2);
		// only do this if we don't have mixed particles
		if(particles.isEmpty()) {
			// add all of our particles for both of our types
			particles.putAll(ElementalUtils.getParticlesForElementalType(this.type1));
			particles.putAll(ElementalUtils.getParticlesForElementalType(this.type2));
		}
		// now spawn our particles
		particles.forEach((type, count) -> {
			// use the world to spawn the particles
			for(int i = 0; i < count; i++) {
				double direction0 = targetToSpawnParticlesAt.getRandom().nextGaussian() * 0.02D;
				double direction1 = targetToSpawnParticlesAt.getRandom().nextGaussian() * 0.02D;
				double direction2 = targetToSpawnParticlesAt.getRandom().nextGaussian() * 0.02D;
				worldServer.sendParticles(type, targetToSpawnParticlesAt.getRandomX(1.0D), targetToSpawnParticlesAt.getRandomY(), targetToSpawnParticlesAt.getRandomZ(1.0D), 0, direction0, direction1, direction2, .1);
			}
		});
	}

	@Override
	public void onUsed(World world, PlayerEntity player, Hand hand) {
		if(this.rightClickEffect != null) {
			this.rightClickEffect.apply(world, player);
		}
	}

	@Override
	public boolean equals(Object other) {
		if(other == this) {
			return true;
		} else if(other instanceof DualSword) {
			return ((DualSword) other).type1 == this.type1 && ((DualSword) other).type2 == this.type2;
		} else {
			return false;
		}
	}
}