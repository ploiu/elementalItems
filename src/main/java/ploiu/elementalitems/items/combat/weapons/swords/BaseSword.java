package ploiu.elementalitems.items.combat.weapons.swords;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.items.ElementalItemsItemRegistry;
import ploiu.elementalitems.items.ItemUtils;
import ploiu.elementalitems.items.combat.weapons.BaseWeapon;
import ploiu.elementalitems.util.EntityUtils;

public abstract class BaseSword extends SwordItem implements BaseWeapon {

	private final ElementalTypes type;

	public BaseSword(ElementalTypes type) {
		super(ItemUtils.getItemTierFromType(type), 5, -2.4F, new Properties().group(ItemGroup.COMBAT));
		this.type = type;
		this.setRegistryName(String.format("sword_%s", type));
		ElementalItemsItemRegistry.addItem(this);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if(EntityUtils.isValidLivingEntity(target)) {
			this.applyEffect(stack, target, attacker);
		}
		return super.hitEntity(stack, target, attacker);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		this.onUsed(worldIn, playerIn, handIn);
		return super.onItemRightClick(worldIn, playerIn, handIn);
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
}
