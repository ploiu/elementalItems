package elementalitems.items.unique;

import elementalitems.ElementalItemsConfig;
import elementalitems.ElementalTypes;
import elementalitems.entities.flamethrower.FlamethrowerEntity;
import elementalitems.items.BaseItem;
import elementalitems.items.ItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class Flamethrower extends BaseItem {

	public Flamethrower() {
		super("flamethrower", ElementalTypes.FIRE);
		this.setCreativeTab(CreativeTabs.COMBAT);
		this.setMaxStackSize(1);
		this.setMaxDamage(500);
		this.addPropertyOverride(new ResourceLocation("charge"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				// 5 stages, but if it's not damaged return 
				int itemDamage = stack.getItemDamage();
				if(itemDamage <= 150 || !stack.isItemDamaged()) {
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

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) {
			// get the item in the hand and make sure it's not damaged
			ItemStack activeItemStack = player.getHeldItem(hand);
			/*
			 * Don't do anything if this item is damaged and has a durability of 0. Sounds simple, right?
			 * WRONG
			 * when an item is undamaged, its durability is 0, so that makes things complicated
			 * a) if we are damaged, and our durability is at 0, we cannot fire
			 * b) if we are damaged, and our durability is not 0, we can fire
			 * c) if we are not damaged, we can fire since our durability is at 0
			 * */
			boolean canFireIfDamaged = activeItemStack.getItemDamage() < activeItemStack.getMaxDamage();
			// items break when they're used and are already at 0% durability. We want to prevent this
			if(ItemHandler.flamethrower.equals(activeItemStack.getItem()) && canFireIfDamaged) {
				FlamethrowerEntity flamethrowerEntity = new FlamethrowerEntity(world, player);
				flamethrowerEntity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 1.5f, 0.0f);
				world.spawnEntity(flamethrowerEntity);
				if(!player.capabilities.isCreativeMode) {
					activeItemStack.damageItem(1, player);
				}
				// play the same sound for a fireball being launched if the config allows it
				if(ElementalItemsConfig.shouldFlamethrowerMakeSound) {
					world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.75F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
				}
			}
		}
		return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
	}

	@Override
	public boolean isDamageable() {
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
