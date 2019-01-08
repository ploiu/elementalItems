package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.combat.swords.dualEffects.IEffect;
import elementalitems.items.combat.swords.dualEffects.IPassiveEffect;
import elementalitems.items.combat.swords.dualEffects.IRightClickEffect;
import elementalitems.util.EntityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DualSword extends BaseSword {

	// the effects that will be used on apply
	private IEffect firstEffect;
	private IEffect secondEffect;
	// the effects to be called in this.onUpdate
	private IPassiveEffect firstPassiveEffect;
	private IPassiveEffect secondPassiveEffect;

	// the right click effect for this sword
	private IRightClickEffect rightClickEffect = null;
	// the types associated with this sword
	private ElementalTypes type1;
	private ElementalTypes type2;

	public DualSword(ElementalTypes type1, ElementalTypes type2, @Nonnull IEffect firstEffect, @Nonnull IEffect secondEffect) {
		this(type1, type2, firstEffect, secondEffect, null, null);
	}

	public DualSword(ElementalTypes type1, ElementalTypes type2, @Nonnull IEffect firstEffect, @Nonnull IEffect secondEffect, @Nullable IPassiveEffect firstPassiveEffect, @Nullable IPassiveEffect secondPassiveEffect) {
		super(ElementalMaterials.getInstance().dualMaterials.get(type1.getTypeName() + "_" + type2.getTypeName()), "sword_" + type1.getTypeName() + "_" + type2.getTypeName(), ElementalTypes.PLAIN);
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
	public String getName() {
		return this.name;
	}


	@Override
	protected boolean applyEffect(EntityLivingBase user, EntityLivingBase target) {
		EntityUtils entityUtils = EntityUtils.getInstance();
		if(entityUtils.isValidEntityLivingBase(user) && entityUtils.isValidEntityLivingBase(target)) {
			// apply both of our effects
			this.firstEffect.apply(user, target);
			this.secondEffect.apply(user, target);
		}
		return false;
	}

	@Override
	protected void specialEffect(World world, EntityPlayer player) {
		if(this.rightClickEffect != null) {
			this.rightClickEffect.apply(world, player);
		}
	}

	@Override
	protected void spawnAttackParticles(WorldServer world, EntityLivingBase targetToSpawnParticlesAt) {
		// TODO take particles in constructor
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
	public void onUpdate(ItemStack stack, World world, Entity owner, int itemSlot, boolean isSelected) {
		if(EntityUtils.getInstance().isValidEntityLivingBase(owner)) {
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
}
