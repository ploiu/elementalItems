package elementalitems.items.combat.armor;

import elementalitems.TestHelper;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Bootstrap;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import static elementalitems.TestHelper.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IceArmorTests {

	@Mock
	EntityPlayer player;
	@Mock
	EntityZombie zombie;
	@Mock
	EntityZombie deadZombie;
	@Mock
	EntityArrow arrow;
	@Mock
	EntityEnderPearl enderPearl;
	@Mock
	InventoryPlayer playerInventory;

	private BaseArmor helmet = null;
	private BaseArmor chestplate = null;
	private BaseArmor leggings = null;
	private BaseArmor boots = null;

	@BeforeAll
	public void setupAll() {
		initMocks(this);
		Bootstrap.register();
		ElementalMaterials.getInstance().registerMaterials();
		ItemHandler.initializeAllItems();
		this.helmet = new IceArmor(HEAD);
		this.chestplate = new IceArmor(CHEST);
		this.leggings = new IceArmor(LEGS);
		this.boots = new IceArmor(FEET);
		TestHelper.setArmorPieces(this.helmet, this.chestplate, this.leggings, this.boots);
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
		when(this.zombie.isEntityAlive()).thenReturn(true);

		// set any fields that require reflection
		TestHelper.setField(this.player, "inventory", this.playerInventory, EntityPlayer.class);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4})
	public void testShouldSlowAndWeakenAttackingMobBasedOnPiecesEquipped(int pieces) {
		fillPlayerArmorInventory(this.playerInventory, pieces);
		attackEntity(this.zombie, this.player);
		verify(this.zombie, atLeastOnce()).addPotionEffect(argThat(matchPotionOfLevel(MobEffects.SLOWNESS, pieces - 1)));
		verify(this.zombie, atLeastOnce()).addPotionEffect(argThat(matchPotionOfLevel(MobEffects.WEAKNESS, pieces - 1)));
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4})
	public void testShouldGiveSpeedToWearerBasedOnPiecesEquipped(int pieces) {
		fillPlayerArmorInventory(this.playerInventory, pieces);
		this.helmet.applyPassiveEffect(this.player);
		verify(this.player, atLeastOnce()).addPotionEffect(argThat(matchPotionOfLevel(MobEffects.SPEED, (pieces - 1) / 2)));
	}

	@Test
	public void testShouldGrantWearerFrostWalkerWhenWearingAFullSet() {
		fillPlayerArmorInventory(this.playerInventory, 4);
		// make sure the player doesn't already have frost walker somehow
		assertFalse(EnchantmentHelper.hasFrostWalkerEnchantment(this.player));
		this.helmet.applyPassiveEffect(this.player);
		assertTrue(EnchantmentHelper.hasFrostWalkerEnchantment(this.player));
	}
}
