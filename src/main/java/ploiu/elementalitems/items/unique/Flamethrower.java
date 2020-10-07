package ploiu.elementalitems.items.unique;

import javafx.geometry.Side;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.BaseItem;

import javax.annotation.Nullable;

public class Flamethrower extends BaseItem {
	public Flamethrower() {
		super(ElementalTypes.FIRE, new Properties().group(ItemGroup.COMBAT).maxDamage(500));
		this.setRegistryName("flamethrower");
		this.itemName = "flamethrower";
		this.addPropertyOverride(new ResourceLocation("charge"), new IItemPropertyGetter() {
			@OnlyIn(Dist.CLIENT)
			public float call(ItemStack stack, @Nullable World worldIn, @Nullable LivingEntity entityIn) {
				// 5 stages, but if it's not damaged return 
				int itemDamage = stack.getDamage();
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
