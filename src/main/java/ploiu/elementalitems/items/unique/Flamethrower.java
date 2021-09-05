package ploiu.elementalitems.items.unique;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.flamethrower.FlamethrowerEntity;
import ploiu.elementalitems.items.BaseItem;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;

import javax.annotation.Nullable;

public class Flamethrower extends BaseItem {
	
	static {
		ItemModelsProperties.register(ElementalItemsItemRegistry.flamethrower, new ResourceLocation("charge"), new IItemPropertyGetter() {
			@OnlyIn(Dist.CLIENT)
			public float call(ItemStack stack, @Nullable ClientWorld worldIn, @Nullable LivingEntity entityIn) {
				// 5 stages, but if it's not damaged return 
				int itemDamage = stack.getDamageValue();
				if(itemDamage <= 150 || !stack.isDamaged()) {
					return 4;
				} else if(itemDamage <= 250) {
					return 3;
				} else if(itemDamage <= 400) {
					return 2;
				} else if(itemDamage <= 480) {
					return 1;
				}
				return 0;
			}
		});
	}
	
	public Flamethrower() {
		super(ElementalTypes.FIRE, new Properties().tab(ItemGroup.TAB_COMBAT).durability(500));
		this.setRegistryName("flamethrower");
		this.itemName = "flamethrower";
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		if(!world.isClientSide()) {
			// get the item in the hand and make sure it's not damaged
			ItemStack activeItemStack = player.getItemInHand(hand);
			/*
			 * Don't do anything if this item is damaged and has a durability of 0. Sounds simple, right?
			 * WRONG
			 * when an item is undamaged, its durability is 0, so that makes things complicated
			 * a) if we are damaged, and our durability is at 0, we cannot fire
			 * b) if we are damaged, and our durability is not 0, we can fire
			 * c) if we are not damaged, we can fire since our durability is at 0
			 * */
			boolean canFireIfDamaged = activeItemStack.getDamageValue() < activeItemStack.getMaxDamage() - 1;
			// items break when they're used and are already at 0% durability. We want to prevent this
			if(ElementalItemsItemRegistry.flamethrower.equals(activeItemStack.getItem()) && canFireIfDamaged) {
				FlamethrowerEntity flamethrowerEntity = new FlamethrowerEntity(player, world);
				flamethrowerEntity.shoot(player, player.xRot, player.yRot, 0.0f, 1.5f, 0.0f);
				if(!world.isClientSide()) {
					world.addFreshEntity(flamethrowerEntity);
				}
				if(!player.abilities.instabuild) {
					activeItemStack.setDamageValue(activeItemStack.getDamageValue() - 1);
				}
				// play the same sound for a fireball being launched if the config allows it TODO
				// if(ElementalItemsConfig.shouldFlamethrowerMakeSound) {
				world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLAZE_SHOOT, SoundCategory.PLAYERS, 0.75F, (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2F + 1.0F);
				// }
			}
		}
		return new ActionResult<>(ActionResultType.FAIL, player.getItemInHand(hand));
	}

	@Override
	public boolean isDamageable(ItemStack stack) {
		return true;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return false;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof Flamethrower;
	}
	
	
}
