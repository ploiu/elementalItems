package elementalitems.items.unique;

import elementalitems.ElementalTypes;
import elementalitems.entities.flamethrower.FlamethrowerEntity;
import elementalitems.items.BaseItem;
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
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if(!world.isRemote) {
			FlamethrowerEntity flamethrowerEntity = new FlamethrowerEntity(world, player);
			flamethrowerEntity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 1.5f, 0.0f);
			world.spawnEntity(flamethrowerEntity);
		}
		return super.onItemRightClick(world, player, hand);
	}
}
