package elementalitems.items.unique;

import elementalitems.ElementalTypes;
import elementalitems.items.ElementalItem;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import elementalitems.util.EntityUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hammer extends ItemTool implements ElementalItem {

	public Hammer() {
		super(ElementalMaterials.getInstance().TOOL_EARTH, Collections.emptySet());
		this.setCreativeTab(CreativeTabs.COMBAT);
		this.setMaxStackSize(1);
		this.setMaxDamage(4000);
		this.attackDamage = ElementalMaterials.getInstance().TOOL_EARTH.getAttackDamage() + 6;
		this.setUnlocalizedName("warhammer");
		this.setRegistryName("warhammer");
		this.attackSpeed = -3.2f;
		ItemHandler.items.add(this);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity target) {
		EntityUtils entityUtils = EntityUtils.getInstance();
		if(entityUtils.isValidEntityLivingBase(target) && entityUtils.isValidEntityLivingBase(player)) {
			EntityLivingBase attackedEntity = (EntityLivingBase) target;
			// if the attacker is a player, we need to look at the cooldown amount before doing damage
			float playerCooldown = player.getCooledAttackStrength(0.5f);
			if(playerCooldown > 0.9f) {
				List<ItemStack> targetArmor = entityUtils.getEntityArmor(attackedEntity);
				// deal a ton of damage to the target's armor
				for(ItemStack armor : targetArmor) {
					armor.damageItem(((int) this.attackDamage * 25), attackedEntity);
				}
				// slow the target
				attackedEntity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30, 1, false, true));
			}
		}
		return false;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		List<Enchantment> whitelistedEnchantments = Arrays.asList(
				Enchantments.BANE_OF_ARTHROPODS,
				Enchantments.SMITE,
				Enchantments.SHARPNESS,
				Enchantments.FIRE_ASPECT,
				Enchantments.KNOCKBACK,
				Enchantments.UNBREAKING
		);
		return whitelistedEnchantments.contains(enchantment);
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity, EntityLivingBase attacker) {
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		// damage the item by 1 instead of the default 2 for tools
		stack.damageItem(1, attacker);
		return true;
	}

	@Override
	public String getName() {
		return "warhammer";
	}

	@Override
	public ElementalTypes getType() {
		return ElementalTypes.EARTH;
	}
}
