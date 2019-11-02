package elementalitems.items.combat.armor;

import elementalitems.TestHelper;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Bootstrap;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import java.util.Arrays;

import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WaterArmorTests {

	@Mock
	EntityPlayer player;
	@Mock
	InventoryPlayer playerInventory;

	private BaseArmor helmet;
	private BaseArmor chestplate;
	private BaseArmor leggings;
	private BaseArmor boots;

	@BeforeAll
	public void setupAll() {
		initMocks(this);
		Bootstrap.register();
		ElementalMaterials.getInstance().registerMaterials();
		ItemHandler.initializeAllItems();
		this.helmet = new WaterArmor(HEAD);
		this.chestplate = new WaterArmor(CHEST);
		this.leggings = new WaterArmor(LEGS);
		this.boots = new WaterArmor(FEET);
	}

	@BeforeEach
	public void setupEach() {
		initMocks(this);
		when(this.player.attackEntityFrom(any(DamageSource.class), anyFloat())).then(invocation -> {
			DamageSource source = invocation.getArgumentAt(0, DamageSource.class);
			this.helmet.onUserHurt(source, this.player);
			this.chestplate.onUserHurt(source, this.player);
			this.leggings.onUserHurt(source, this.player);
			this.boots.onUserHurt(source, this.player);
			return true;
		});
		when(this.player.getArmorInventoryList()).thenCallRealMethod();
		when(this.player.getItemStackFromSlot(any(EntityEquipmentSlot.class))).thenCallRealMethod();
		when(this.player.isEntityAlive()).thenReturn(true);
		when(this.player.getAir()).thenCallRealMethod();
		when(this.player.isInWater()).thenReturn(true);

		// set any fields that require reflection
		TestHelper.setField(this.player, "inventory", this.playerInventory, EntityPlayer.class);
	}

	@Test
	public void testProvideWearerWithInfiniteAirWhileWearingAFullSet() {
		this.fillPlayerArmorInventory();
		this.helmet.applyPassiveEffect(this.player);
		verify(this.player, atLeastOnce()).setAir(eq(300));
	}

	@Test
	public void testShouldNotProvideWearerWithInfiniteAirWhenNotWearingFullSet() {
		this.fillPlayerArmorInventory(3);
		this.helmet.applyPassiveEffect(this.player);
		verify(this.player, never()).setAir(anyInt());
	}

	@Test
	public void testShouldProvideWearerWithDepthStriderWhenWearingAFullSet() {
		this.fillPlayerArmorInventory();
		// make sure that the player doesn't somehow already have depth strider
		assertEquals(0, EnchantmentHelper.getDepthStriderModifier(this.player));
		// now actually apply the effect
		this.helmet.applyPassiveEffect(this.player);
		assertEquals(3, EnchantmentHelper.getDepthStriderModifier(this.player));
	}

	@Test
	public void testShouldNotApplyDepthStriderWhenWearerIsNotWearingAFullSet() {
		this.fillPlayerArmorInventory(3);
		this.helmet.applyPassiveEffect(this.player);
		assertEquals(0, EnchantmentHelper.getDepthStriderModifier(this.player));
	}

	@Test
	public void testShouldRemoveDepthStriderWhenWearerIsOutOfWaterAndIsOutOfWater() {
		this.fillPlayerArmorInventory();
		// apply the effect so that we can verify it was actually removed
		this.helmet.applyPassiveEffect(this.player);
		assertEquals(3, EnchantmentHelper.getDepthStriderModifier(this.player));
		// take the player out of the water and check again
		when(this.player.isInWater()).thenReturn(false);
		this.helmet.applyPassiveEffect(this.player);
		assertEquals(0, EnchantmentHelper.getDepthStriderModifier(this.player));
	}

	private void setPlayerArmorInventory(ItemStack... pieces) {
		this.clearPlayerArmorInventory();
		TestHelper.setField(this.playerInventory, "armorInventory", NonNullList.from(new ItemStack(Items.AIR), pieces), InventoryPlayer.class);
	}

	private void clearPlayerArmorInventory() {
		TestHelper.setField(this.playerInventory, "armorInventory", NonNullList.withSize(4, new ItemStack(Items.AIR)), InventoryPlayer.class);
	}


	private void fillPlayerArmorInventory() {
		this.setPlayerArmorInventory(new ItemStack(this.helmet), new ItemStack(this.chestplate), new ItemStack(this.leggings), new ItemStack(this.boots));
	}

	private void fillPlayerArmorInventory(int piecesToWear) {
		ItemStack[] armorPieces = new ItemStack[]{new ItemStack(this.helmet), new ItemStack(this.chestplate), new ItemStack(this.leggings), new ItemStack(this.boots)};
		Arrays.fill(armorPieces, piecesToWear, armorPieces.length, new ItemStack(Items.AIR));
		this.setPlayerArmorInventory(armorPieces);
	}
}
