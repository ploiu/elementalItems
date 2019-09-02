package elementalitems.items.unique;

import elementalitems.ElementalTypes;
import elementalitems.items.ElementalItem;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import elementalitems.util.EntityUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

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
		this.attackSpeed = -5.0f;
		ItemHandler.items.add(this);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		EntityUtils entityUtils = EntityUtils.getInstance();
		if(entityUtils.isValidEntityLivingBase(target) && entityUtils.isValidEntityLivingBase(attacker)) {
			// TODO deal extra damage to armor, slow target
			List<ItemStack> targetArmor = entityUtils.getEntityArmor(target);
			// deal half of our damage to the armor
			for(ItemStack armor : targetArmor) {
				armor.damageItem(((int) this.attackDamage * 30), target);
			}
		}
		return true;
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
