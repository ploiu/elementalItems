package elementalitems.items.combat.armor;

import elementalitems.TestHelper;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Bootstrap;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.potion.PotionEffect;
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
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FireArmorTests {

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
		this.helmet = new FireArmor(HEAD);
		this.chestplate = new FireArmor(CHEST);
		this.leggings = new FireArmor(LEGS);
		this.boots = new FireArmor(FEET);
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
	public void testFireArmorShouldSetAttackerOnFireDependingOnAmountOfPiecesWorn(int piecesToWear) {
		fillPlayerArmorInventory(this.playerInventory, piecesToWear);
		// attack our player
		attackEntity(this.zombie, this.player);
		verify(this.zombie, atLeastOnce()).setFire(eq(piecesToWear * 5));
	}

	@Test
	public void testShouldNotSetNullEntitiesOnFire() {
		fillPlayerArmorInventory(this.playerInventory, 4);
		attackEntity(null, this.player);
		// if we don't throw a null pointer, the test passes
	}

	@Test
	public void testShouldNotSetDeadEntitiesOnFire() {
		fillPlayerArmorInventory(this.playerInventory, 4);
		attackEntity(this.deadZombie, this.player);
		verify(this.deadZombie, never()).setFire(anyInt());
	}

	@Test
	public void testShouldNotSetProjectilesOnFire() {
		fillPlayerArmorInventory(this.playerInventory, 4);
		attackEntity(this.arrow, this.player);
		verify(this.arrow, never()).setFire(anyInt());
	}

	@Test
	public void testShouldNotAttemptToSetAnythingOnFireWhenDamagedByEnderPearl() {
		fillPlayerArmorInventory(this.playerInventory, 4);
		this.player.attackEntityFrom(DamageSource.causeThrownDamage(this.enderPearl, this.player), 0F);
		verify(this.player, never()).setFire(anyInt());
		verify(this.enderPearl, never()).setFire(anyInt());
	}

	@Test
	public void testShouldNotAttemptToSetAnythingOnFireWhenWearerTakesFallDamage() {
		fillPlayerArmorInventory(this.playerInventory, 4);
		this.player.attackEntityFrom(DamageSource.FALL, 0F);
	}

	@Test
	public void testFireArmorShouldGrantFireResistanceWhenWearerHasFullSet() {
		fillPlayerArmorInventory(this.playerInventory, 4);
		this.helmet.applyPassiveEffect(this.player);
		verify(this.player, atLeastOnce()).addPotionEffect(argThat(matchPotionEffect(MobEffects.FIRE_RESISTANCE)));
	}

	@Test
	public void testFireArmorShouldNotGrantFireResistanceWhenWearerHasLessThanFullSet() {
		fillPlayerArmorInventory(this.playerInventory, 3);
		this.helmet.applyPassiveEffect(this.player);
		verify(this.player, never()).addPotionEffect(any(PotionEffect.class));
	}
}
