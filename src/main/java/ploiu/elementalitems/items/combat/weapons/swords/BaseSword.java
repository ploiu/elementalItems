package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.items.combat.weapons.BaseWeapon;
import ploiu.elementalitems.util.ElementalUtils;
import ploiu.elementalitems.util.EntityUtils;
import ploiu.elementalitems.util.ItemUtils;

import java.util.Map;

public abstract class BaseSword extends SwordItem implements BaseWeapon {

	protected final String name;
	private final ElementalTypes type;

	public BaseSword(ElementalTypes type) {
		super(ItemUtils.getItemTierFromType(type), 5, -2.4F, new Properties().group(ItemGroup.TAB_COMBAT));
		this.type = type;
		this.name = String.format("sword_%s", type);
		this.setRegistryName(this.name);
		ElementalItemsItemRegistry.addItem(this);
	}

	protected BaseSword(ElementalTypes type, float attackSpeed) {
		super(ItemUtils.getItemTierFromType(type), 5, attackSpeed, new Properties().group(ItemGroup.TAB_COMBAT));
		this.type = type;
		this.name = String.format("sword_%s", type);
		this.setRegistryName(this.name);
		ElementalItemsItemRegistry.addItem(this);
	}

	public BaseSword(IItemTier tier, String name, ElementalTypes type) {
		super(tier, 5, -2.4f, new Properties().group(ItemGroup.TAB_COMBAT));
		this.type = type;
		this.name = name;
		this.setRegistryName(this.name);
		ElementalItemsItemRegistry.addItem(this);
	}

	protected abstract void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected);

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if(EntityUtils.isValidLivingEntity(target)) {
			if(target.getEntityWorld() instanceof ServerWorld) {
				this.spawnAttackParticles((ServerWorld) target.getEntityWorld(), target);
			}
			this.applyEffect(stack, target, attacker);
		}
		return super.hitEntity(stack, target, attacker);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		this.onUsed(worldIn, playerIn, handIn);
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
		this.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	/**
	 * attempts to spawn particles based on our {@link ElementalTypes} when this sword hits a mob
	 *
	 * @param worldServer              the {@link ServerWorld} object we're using to spawn the particles
	 * @param targetToSpawnParticlesAt the {@link LivingEntity} we're spawning the particles at
	 */
	protected void spawnAttackParticles(ServerWorld worldServer, LivingEntity targetToSpawnParticlesAt) {
		Map<IParticleData, Integer> particlesToSpawn = ElementalUtils.getParticlesForElementalType(this.type);
		// spawn all the particle types associated with this
		particlesToSpawn.forEach((type, count) -> {
			// use the worldServer to spawn the particles
			worldServer.spawnParticle(type, targetToSpawnParticlesAt.getX(), targetToSpawnParticlesAt.getY(), targetToSpawnParticlesAt.getZ(), count, targetToSpawnParticlesAt.getWidth(), targetToSpawnParticlesAt.getHeight(), targetToSpawnParticlesAt.getWidth(), 0.0);
		});
	}

	/**
	 * called when the sword is right clicked when in the player's hand
	 *
	 * @param world
	 * @param player
	 * @param hand
	 */
	public abstract void onUsed(World world, PlayerEntity player, Hand hand);

	@Override
	public ElementalTypes getType() {
		return this.type;
	}

	@Override
	public boolean equals(Object other) {
		if(other == this) {
			return true;
		} else if(other instanceof BaseSword) {
			return ((BaseSword) other).getType() == this.type;
		} else {
			return false;
		}
	}
}
