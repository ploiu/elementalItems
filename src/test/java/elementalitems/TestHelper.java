package elementalitems;

import elementalitems.items.combat.armor.BaseArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.NonNullList;
import org.mockito.internal.matchers.VarargCapturingMatcher;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.Arrays;

import static net.minecraft.util.DamageSource.GENERIC;

public class TestHelper {

	public static BaseArmor helmet, chestplate, leggings, boots;

	public static boolean doPotionsMatch(@Nonnull PotionEffect toTest, Potion effect, int duration, int power, boolean ambient, boolean showParticles) {
		return toTest.getPotion().equals(effect) && toTest.getDuration() == duration && toTest.getAmplifier() == power && toTest.getIsAmbient() == ambient && toTest.doesShowParticles() == showParticles;
	}

	public static void setField(Object toSet, String fieldName, Object fieldValue, Class<?> classWithField) {
		try {
			Field field = classWithField.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(toSet, fieldValue);
		} catch(NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static VarargCapturingMatcher<PotionEffect> matchPotionOfLevel(Potion potion, int level) {
		return new VarargCapturingMatcher<PotionEffect>() {
			@Override
			public boolean matches(Object argument) {
				return (argument instanceof PotionEffect) && (((PotionEffect) argument).getAmplifier() == level) && (((PotionEffect) argument).getPotion().equals(potion));
			}
		};
	}

	public static VarargCapturingMatcher<PotionEffect> matchPotionEffect(Potion potion) {
		return new VarargCapturingMatcher<PotionEffect>() {
			@Override
			public boolean matches(Object argument) {
				return argument instanceof PotionEffect && ((PotionEffect) argument).getPotion().equals(potion);
			}
		};
	}

	public static VarargCapturingMatcher<Potion> matchPotion(Potion potion) {
		return new VarargCapturingMatcher<Potion>() {
			@Override
			public boolean matches(Object argument) {
				return argument.equals(potion);
			}
		};
	}

	public static void attackEntity(Entity attacker, Entity target) {
		target.attackEntityFrom(new EntityDamageSource(GENERIC.damageType, attacker), 0F);
	}

	public static void clearArmorInventory(InventoryPlayer playerInventory) {
		setField(playerInventory, "armorInventory", NonNullList.withSize(4, new ItemStack(Items.AIR)), InventoryPlayer.class);
	}

	public static void setArmorInventory(InventoryPlayer playerInventory, Item... pieces) {
		// convert the pieces to item stacks
		ItemStack[] stacks = new ItemStack[pieces.length];
		for(int i = 0; i < pieces.length; i++) {
			stacks[i] = new ItemStack(pieces[i]);
		}
		clearArmorInventory(playerInventory);
		setField(playerInventory, "armorInventory", NonNullList.from(new ItemStack(Items.AIR), stacks), InventoryPlayer.class);
	}

	public static void fillPlayerArmorInventory(InventoryPlayer playerInventory, int piecesToWear) {
		Item[] armorPieces = new Item[]{helmet, chestplate, leggings, boots};
		Arrays.fill(armorPieces, piecesToWear, armorPieces.length, Items.AIR);
		setArmorInventory(playerInventory, armorPieces);
	}

	public static void setArmorPieces(BaseArmor helmet1, BaseArmor chestplate1, BaseArmor leggings1, BaseArmor boots1) {
		helmet = helmet1;
		chestplate = chestplate1;
		leggings = leggings1;
		boots = boots1;
	}
}
