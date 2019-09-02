package elementalitems.items.unique;

import elementalitems.ElementalTypes;
import elementalitems.items.ElementalItem;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import elementalitems.util.EntityUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.CooldownTracker;

import java.util.Collections;
import java.util.List;

public class Hammer extends ItemTool implements ElementalItem {

	public Hammer() {
		super(ElementalMaterials.getInstance().TOOL_EARTH, Collections.emptySet());
		this.setCreativeTab(CreativeTabs.COMBAT);
		this.setMaxStackSize(1);
		this.setMaxDamage(4000);
		this.attackDamage = ElementalMaterials.getInstance().TOOL_EARTH.getAttackDamage();
		this.setUnlocalizedName("hammer");
		this.setRegistryName("hammer");
		this.attackSpeed = -3.3f;
		ItemHandler.items.add(this);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity target) {
		EntityUtils entityUtils = EntityUtils.getInstance();
		if(entityUtils.isValidEntityLivingBase(target) && entityUtils.isValidEntityLivingBase(player)) {
			EntityLivingBase attackedEntity = (EntityLivingBase)target;
			// if the attacker is a player, we need to look at the cooldown amount before doing damage
			float playerCooldown = player.getCooledAttackStrength(0.5f);
			if(playerCooldown > 0.9f) {
				// TODO deal extra damage to armor, slow target
				List<ItemStack> targetArmor = entityUtils.getEntityArmor(attackedEntity);
				// deal half of our damage to the armor
				for(ItemStack armor : targetArmor) {
					armor.damageItem(((int) this.attackDamage * 30), attackedEntity);
				}
			}
		}
		return false;
	}

	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity, EntityLivingBase attacker) {
		return true;
	}

	@Override
	public String getName() {
		return "hammer";
	}

	@Override
	public ElementalTypes getType() {
		return ElementalTypes.EARTH;
	}
}
