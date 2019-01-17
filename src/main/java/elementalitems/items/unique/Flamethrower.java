package elementalitems.items.unique;

import elementalitems.ElementalTypes;
import elementalitems.entities.flamethrower.FlamethrowerEntity;
import elementalitems.items.BaseItem;
import elementalitems.items.ItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Flamethrower extends BaseItem {

	public Flamethrower() {
		super("flamethrower", ElementalTypes.FIRE);
		this.setCreativeTab(CreativeTabs.COMBAT);
		this.setMaxStackSize(1);
		this.setMaxDamage(500);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) {
			// get the item in the hand and make sure it's not damaged
			ItemStack activeItemStack = player.getHeldItem(hand);
			// items break when they're used and are already at 0% durability. We want to prevent this
			if(ItemHandler.flamethrower.equals(activeItemStack.getItem()) && (this.getDamage(activeItemStack) > 0 || !activeItemStack.isItemDamaged())) {
				FlamethrowerEntity flamethrowerEntity = new FlamethrowerEntity(world, player);
				flamethrowerEntity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 1.5f, 0.0f);
				world.spawnEntity(flamethrowerEntity);
			}
		}
		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public boolean isDamageable() {
		return true;
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof Flamethrower;
	}
}
