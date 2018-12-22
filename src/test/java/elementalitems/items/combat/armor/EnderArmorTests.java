package elementalitems.items.combat.armor;

import elementalitems.TestHelper;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Bootstrap;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import static elementalitems.TestHelper.fillPlayerArmorInventory;
import static elementalitems.TestHelper.matchPotionEffect;
import static net.minecraft.init.MobEffects.*;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EnderArmorTests {

	@Mock
	EntityPlayer player;
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
		this.helmet = new EnderArmor(HEAD);
		this.chestplate = new EnderArmor(CHEST);
		this.leggings = new EnderArmor(LEGS);
		this.boots = new EnderArmor(FEET);
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

		// set any fields that require reflection
		TestHelper.setField(this.player, "inventory", this.playerInventory, EntityPlayer.class);
	}


	@Test
	public void testShouldApplyJumpBoostWhenOnePieceIsEquipped() {
		fillPlayerArmorInventory(this.playerInventory, 1);
		this.helmet.applyPassiveEffect(this.player);
		// only jump boost, nothing else
		verify(this.player).addPotionEffect(argThat(matchPotionEffect(JUMP_BOOST)));
		// don't add the other effects
		verify(this.player, never()).addPotionEffect(argThat(matchPotionEffect(NIGHT_VISION)));
		verify(this.player, never()).addPotionEffect(argThat(matchPotionEffect(SPEED)));
		verify(this.player, never()).addPotionEffect(argThat(matchPotionEffect(LUCK)));
	}

	@Test
	public void testShouldApplyNightVisionWhenTwoPiecesAreEquipped() {
		fillPlayerArmorInventory(this.playerInventory, 2);
		this.helmet.applyPassiveEffect(this.player);
		verify(this.player).addPotionEffect(argThat(matchPotionEffect(JUMP_BOOST)));
		verify(this.player).addPotionEffect(argThat(matchPotionEffect(NIGHT_VISION)));
		// don't add the other effects
		verify(this.player, never()).addPotionEffect(argThat(matchPotionEffect(SPEED)));
		verify(this.player, never()).addPotionEffect(argThat(matchPotionEffect(LUCK)));
	}

	@Test
	public void testShouldApplySpeedWhenThreePiecesAreEquipped() {
		fillPlayerArmorInventory(this.playerInventory, 3);
		this.helmet.applyPassiveEffect(this.player);
		verify(this.player).addPotionEffect(argThat(matchPotionEffect(JUMP_BOOST)));
		verify(this.player).addPotionEffect(argThat(matchPotionEffect(NIGHT_VISION)));
		verify(this.player).addPotionEffect(argThat(matchPotionEffect(SPEED)));
		// don't add the other effects
		verify(this.player, never()).addPotionEffect(argThat(matchPotionEffect(LUCK)));
	}

	@Test
	public void testShouldApplyLuckWhenFourPiecesAreEquipped() {
		fillPlayerArmorInventory(this.playerInventory, 4);
		this.helmet.applyPassiveEffect(this.player);
		verify(this.player).addPotionEffect(argThat(matchPotionEffect(JUMP_BOOST)));
		verify(this.player).addPotionEffect(argThat(matchPotionEffect(NIGHT_VISION)));
		verify(this.player).addPotionEffect(argThat(matchPotionEffect(SPEED)));
		verify(this.player).addPotionEffect(argThat(matchPotionEffect(LUCK)));
	}
}
