package elementalitems.items.combat.swords;

import elementalitems.ElementalType;
import elementalitems.TestHelper;
import elementalitems.items.ElementalMaterials;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.VarargCapturingMatcher;
import org.mockito.stubbing.Answer;

import java.util.Random;

import static elementalitems.TestHelper.doPotionsMatch;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class SwordTests {

	private FireSword fireSword;
	private IceSword iceSword;
	private EnderSword enderSword;
	private LifeDeathSword lifeDeathSword;
	private EarthSword earthSword;

	private BaseSword swordBeingTested;

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

	// this is needed else the game throws a fit when we try to use its resources
	static {
		Bootstrap.register();
	}

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		fireSword = new FireSword(ElementalMaterials.getInstance().TOOL_FIRE, "fakeSword", ElementalType.FIRE);
		iceSword = new IceSword(ElementalMaterials.getInstance().TOOL_ICE, "fakeSword", ElementalType.ICE);
		enderSword = new EnderSword(ElementalMaterials.getInstance().TOOL_ENDER, "fakeSword", ElementalType.ENDER);
		lifeDeathSword = new LifeDeathSword(ElementalMaterials.getInstance().TOOL_PLAIN, "fakeSword", ElementalType.PLAIN);

		when(manager.get(anyObject())).thenAnswer((Answer<Float>) invocation -> currentHealth);
		when(fakePlayer.attackEntityAsMob(any(Entity.class))).thenAnswer((Answer<Boolean>) invocation -> {
			swordBeingTested.applyEffect(fakePlayer, fakeZombie);
			return false;
		});
		when(fakePlayer.getEntityAttribute(eq(SharedMonsterAttributes.MAX_HEALTH))).thenReturn(maxHealth);
		when(maxHealth.getAttributeValue()).thenReturn(20.0);
		when(fakeZombie.getEntityWorld()).thenReturn(world);
		when(fakeZombie.isEntityAlive()).thenReturn(true);
		when(fakeZombie.isPotionApplicable(any(PotionEffect.class))).thenReturn(true);
		when(fakeZombie.getRNG()).thenReturn(new Random());
		when(fakeZombie.getPosition()).thenReturn(position);
		when(position.getX()).thenReturn(1);
		when(position.getY()).thenReturn(64);
		when(position.getZ()).thenReturn(2);

		TestHelper.setField(fakePlayer, "dataManager", manager, Entity.class);

	}

	@Test
	public void testFireSwordSetsTargetOnFire() {
		swordBeingTested = fireSword;
		fakePlayer.attackEntityAsMob(fakeZombie);
		verify(fakeZombie).setFire(eq(10));
	}

	@Test
	public void testIceSwordSlowsAndWeakensTarget() {
		swordBeingTested = iceSword;
		fakePlayer.attackEntityAsMob(fakeZombie);
		verify(fakeZombie).addPotionEffect(argThat(new VarargCapturingMatcher<PotionEffect>() {
			@Override
			public boolean matches(Object item) {
				PotionEffect effect = (PotionEffect) item;
				return doPotionsMatch(effect, MobEffects.SLOWNESS, 100, 2, false, true);
			}
		}));
		verify(fakeZombie).addPotionEffect(argThat(new VarargCapturingMatcher<PotionEffect>() {
			@Override
			public boolean matches(Object item) {
				PotionEffect effect = (PotionEffect) item;
				return doPotionsMatch(effect, MobEffects.WEAKNESS, 100, 2, false, true);

			}
		}));
	}

	@Test(expected = NullPointerException.class)
	public void testIceSwordSpawnsSnowballWhenUsed() {
		iceSword.specialEffect(world, fakePlayer, null);
		verify(world).spawnEntity(isA(EntitySnowball.class));
	}

	@Test
	public void testEnderSwordAttemptsTeleport() {
		swordBeingTested = enderSword;
		fakePlayer.attackEntityAsMob(fakeZombie);
		verify(fakeZombie).attemptTeleport(anyDouble(), anyDouble(), anyDouble());
	}

	@Test(expected = NullPointerException.class)
	public void testEnderSwordSpawnsEnderPearlWhenUsed() {
		enderSword.specialEffect(world, fakePlayer, null);
		verify(world).spawnEntity(isA(EntityEnderPearl.class));
	}

	@Test
	public void testLifeDeathSwordHealsBasedOnUserHealth() {
		swordBeingTested = lifeDeathSword;
		float expectedExtraDamage;
		// mock the player being at full health
		currentHealth = 20f;
		expectedExtraDamage = 0f;
		fakePlayer.attackEntityAsMob(fakeZombie);
		verify(fakeZombie).attackEntityFrom(eq(DamageSource.OUT_OF_WORLD), eq(expectedExtraDamage));
		verify(fakePlayer).heal(eq(expectedExtraDamage));

		// half a heart
		currentHealth = 0.5f;
		expectedExtraDamage = 14.625f;
		fakePlayer.attackEntityAsMob(fakeZombie);
		verify(fakeZombie).attackEntityFrom(eq(DamageSource.OUT_OF_WORLD), eq(expectedExtraDamage));
		verify(fakePlayer).heal(eq(expectedExtraDamage));
	}

}
