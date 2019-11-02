package elementalitems.items.combat.armor;

import elementalitems.TestHelper;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Bootstrap;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static elementalitems.TestHelper.*;
import static net.minecraft.init.MobEffects.REGENERATION;
import static net.minecraft.init.MobEffects.WITHER;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LeafArmorTests {

	@Mock
	EntityPlayer player;
	@Mock
	EntityZombie zombie;
	@Mock
	EntityZombie deadZombie;
	@Mock
	EntitySpider spider;
	@Mock
	EntityArrow arrow;
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
		this.helmet = new LeafArmor(HEAD);
		this.chestplate = new LeafArmor(CHEST);
		this.leggings = new LeafArmor(LEGS);
		this.boots = new LeafArmor(FEET);
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
		when(this.zombie.isEntityUndead()).thenReturn(true);
		when(this.deadZombie.isEntityUndead()).thenReturn(true);
		when(this.spider.isEntityAlive()).thenReturn(true);

		// set any fields that require reflection
		TestHelper.setField(this.player, "inventory", this.playerInventory, EntityPlayer.class);
	}

	@TestFactory
	public List<DynamicTest> leafArmorUserHurtTests() {
		return Arrays.asList(
				dynamicTest("should not damage an attacker if it's already dead", () -> {
					fillPlayerArmorInventory(this.playerInventory, 4);
					attackEntity(this.deadZombie, this.player);
					verify(this.deadZombie, never()).attackEntityFrom(any(DamageSource.class), anyFloat());
				}),
				dynamicTest("should not attempt to damage anything if attacker is null", () -> {
					fillPlayerArmorInventory(this.playerInventory, 4);
					attackEntity(null, this.player);
					// if an exception is thrown then the test fails, if not, then it passes
				}),
				dynamicTest("should not attempt to attack a projectile", () -> {
					fillPlayerArmorInventory(this.playerInventory, 4);
					attackEntity(this.arrow, this.player);
					verify(this.arrow, never()).attackEntityFrom(any(DamageSource.class), anyFloat());
				}),
				dynamicTest("should not attempt to attack a non-undead entity", () -> {
					fillPlayerArmorInventory(this.playerInventory, 4);
					attackEntity(this.spider, this.player);
					verify(this.spider, never()).attackEntityFrom(any(DamageSource.class), anyFloat());
				})
		);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4})
	public void testShouldDamageUndeadAttackersBasedOnNumberOfPiecesWorn(int piecesToWear) {
		fillPlayerArmorInventory(this.playerInventory, piecesToWear);
		attackEntity(this.zombie, this.player);
		verify(this.zombie, atLeastOnce()).attackEntityFrom(any(DamageSource.class), eq((float) piecesToWear * (float) piecesToWear));
	}

	@Test
	public void testShouldRemoveWitherWhenEquipped() {
		fillPlayerArmorInventory(this.playerInventory, 1);
		this.helmet.applyPassiveEffect(this.player);
		verify(this.player).removePotionEffect(argThat(matchPotion(WITHER)));
	}

	@Test
	public void testShouldApplyRegenIIIWhenWearerIsAboveLevelThirty() {
		this.player.experienceLevel = 50;
		fillPlayerArmorInventory(this.playerInventory, 1);
		this.helmet.applyPassiveEffect(this.player);
		verify(this.player).addPotionEffect(argThat(matchPotionOfLevel(REGENERATION, 2)));
	}

	@Test
	public void testShouldApplyRegenIWhenWearerIsBelowLevelTen() {
		this.player.experienceLevel = 0;
		fillPlayerArmorInventory(this.playerInventory, 1);
		this.helmet.applyPassiveEffect(this.player);
		verify(this.player).addPotionEffect(argThat(matchPotionOfLevel(REGENERATION, 0)));
	}
}
