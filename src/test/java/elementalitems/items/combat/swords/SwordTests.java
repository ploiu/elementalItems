package elementalitems.items.combat.swords;

import elementalitems.ElementalTypes;
import elementalitems.TestHelper;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Bootstrap;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.VarargCapturingMatcher;
import org.mockito.stubbing.Answer;

import java.util.Random;

import static elementalitems.TestHelper.doPotionsMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SwordTests {

	// this is needed else the game throws a fit when we try to use its resources
	static {
		Bootstrap.register();
	}

	@Mock
	EntityZombie fakeZombie;
	@Mock
	EntityPlayer fakePlayer;
	@Mock
	IAttributeInstance maxHealth;
	@Mock
	EntityDataManager manager;
	@Mock
	BlockPos position;
	@Mock
	World world;
	
	Float currentHealth;
	private FireSword fireSword;
	private IceSword iceSword;
	private EnderSword enderSword;
	private LifeDeathSword lifeDeathSword;
	private EarthSword earthSword;
	private BaseSword swordBeingTested;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Bootstrap.register();
		ElementalMaterials.getInstance().registerMaterials();
		ItemHandler.initializeAllItems();

		this.fireSword = new FireSword(ElementalMaterials.getInstance().TOOL_FIRE, "fakeSword", ElementalTypes.FIRE);
		this.iceSword = new IceSword(ElementalMaterials.getInstance().TOOL_ICE, "fakeSword", ElementalTypes.ICE);
		this.enderSword = new EnderSword(ElementalMaterials.getInstance().TOOL_ENDER, "fakeSword", ElementalTypes.ENDER);
		this.lifeDeathSword = new LifeDeathSword(ElementalMaterials.getInstance().TOOL_PLAIN, "fakeSword", ElementalTypes.PLAIN);

		when(this.manager.get(anyObject())).thenAnswer((Answer<Float>) invocation -> this.currentHealth);
		when(this.fakePlayer.attackEntityAsMob(any(Entity.class))).thenAnswer((Answer<Boolean>) invocation -> this.swordBeingTested.applyEffect(this.fakePlayer, this.fakeZombie));
		when(this.fakePlayer.getEntityAttribute(eq(SharedMonsterAttributes.MAX_HEALTH))).thenReturn(this.maxHealth);
		when(this.maxHealth.getAttributeValue()).thenReturn(20.0);
		when(this.fakeZombie.getEntityWorld()).thenReturn(this.world);
		when(this.fakeZombie.isEntityAlive()).thenReturn(true);
		when(this.fakeZombie.isPotionApplicable(any(PotionEffect.class))).thenReturn(true);
		when(this.fakeZombie.getRNG()).thenReturn(new Random());
		when(this.fakeZombie.getPosition()).thenReturn(this.position);
		when(this.position.getX()).thenReturn(1);
		when(this.position.getY()).thenReturn(64);
		when(this.position.getZ()).thenReturn(2);

		TestHelper.setField(this.fakePlayer, "dataManager", this.manager, Entity.class);
	}

	@Test
	public void testFireSwordSetsTargetOnFire() {
		this.swordBeingTested = this.fireSword;
		this.fakePlayer.attackEntityAsMob(this.fakeZombie);
		verify(this.fakeZombie).setFire(eq(10));
	}

	@Test
	public void testIceSwordSlowsAndWeakensTarget() {
		this.swordBeingTested = this.iceSword;
		this.fakePlayer.attackEntityAsMob(this.fakeZombie);
		verify(this.fakeZombie).addPotionEffect(argThat(new VarargCapturingMatcher<PotionEffect>() {
			@Override
			public boolean matches(Object item) {
				PotionEffect effect = (PotionEffect) item;
				return doPotionsMatch(effect, MobEffects.SLOWNESS, 100, 2, false, true);
			}
		}));
		verify(this.fakeZombie).addPotionEffect(argThat(new VarargCapturingMatcher<PotionEffect>() {
			@Override
			public boolean matches(Object item) {
				PotionEffect effect = (PotionEffect) item;
				return doPotionsMatch(effect, MobEffects.WEAKNESS, 100, 2, false, true);
			}
		}));
	}

	@Test
	public void testIceSwordSpawnsSnowballWhenUsed() {
		assertThrows(NullPointerException.class, () -> {
			this.iceSword.specialEffect(this.world, this.fakePlayer);
			verify(this.world).spawnEntity(isA(EntitySnowball.class));
		});
	}

	@Test
	public void testEnderSwordAttemptsTeleport() {
		this.swordBeingTested = this.enderSword;
		this.fakePlayer.attackEntityAsMob(this.fakeZombie);
		verify(this.fakeZombie).attemptTeleport(anyDouble(), anyDouble(), anyDouble());
	}

	@Test
	public void testEnderSwordSpawnsEnderPearlWhenUsed() {
		assertThrows(NullPointerException.class, () -> {
			this.enderSword.specialEffect(this.world, this.fakePlayer);
			verify(this.world).spawnEntity(isA(EntityEnderPearl.class));
		});
	}

	@Test
	public void testLifeDeathSwordHealsBasedOnUserHealth() {
		this.swordBeingTested = this.lifeDeathSword;
		float expectedExtraDamage;
		// mock the player being at full health
		this.currentHealth = 20f;
		expectedExtraDamage = 0f;
		this.fakePlayer.attackEntityAsMob(this.fakeZombie);
		verify(this.fakeZombie).attackEntityFrom(eq(DamageSource.OUT_OF_WORLD), eq(expectedExtraDamage));
		verify(this.fakePlayer).heal(eq(expectedExtraDamage));

		// half a heart
		this.currentHealth = 0.5f;
		expectedExtraDamage = 14.625f;
		this.fakePlayer.attackEntityAsMob(this.fakeZombie);
		verify(this.fakeZombie).attackEntityFrom(eq(DamageSource.OUT_OF_WORLD), eq(expectedExtraDamage));
		verify(this.fakePlayer).heal(eq(expectedExtraDamage));
	}
}
