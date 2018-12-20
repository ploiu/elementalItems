package elementalitems.items.combat.armor;

import elementalitems.TestHelper;
import elementalitems.items.ElementalMaterials;
import elementalitems.items.ItemHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Bootstrap;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static elementalitems.TestHelper.*;
import static net.minecraft.init.MobEffects.FIRE_RESISTANCE;
import static net.minecraft.init.MobEffects.REGENERATION;
import static net.minecraft.inventory.EntityEquipmentSlot.*;
import static net.minecraft.util.DamageSource.GENERIC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArmorTests {
	// this is needed else the game throws a fit when we try to use its resources
	static {
	}

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
	EntityEnderPearl enderPearl;
	@Mock
	World world;
	@Mock
	InventoryPlayer playerInventory;

	private BaseArmor helmet;
	private BaseArmor chestplate;
	private BaseArmor leggings;
	private BaseArmor boots;

	// a normal set of armor
	private ItemArmor normalHelmet;
	private ItemArmor normalChestplate;
	private ItemArmor normalLeggings;
	private ItemArmor normalBoots;


	@BeforeAll
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Bootstrap.register();
		ElementalMaterials.getInstance().registerMaterials();
		ItemHandler.initializeAllItems();
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
		when(this.zombie.isEntityAlive()).thenReturn(true);
		when(this.zombie.getArmorInventoryList()).thenReturn(NonNullList.withSize(4, new ItemStack(Items.AIR)));
		when(this.zombie.isEntityUndead()).thenReturn(true);
		when(this.deadZombie.isEntityUndead()).thenReturn(true);
		when(this.spider.isEntityAlive()).thenReturn(true);

		// initialize our non-mocks
		this.normalHelmet = new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, -1, HEAD);
		this.normalChestplate = new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, -1, CHEST);
		this.normalLeggings = new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, -1, LEGS);
		this.normalBoots = new ItemArmor(ItemArmor.ArmorMaterial.DIAMOND, -1, FEET);

		// set any fields that require reflection
		TestHelper.setField(this.player, "inventory", this.playerInventory, EntityPlayer.class);
	}

	@TestFactory
	public List<DynamicTest> fireArmorUserHurtTests() {
		this.initAsFireArmor();
		return Arrays.asList(
				dynamicTest("should set attacker on fire for 20 seconds when attacker is an EntityLivingBase and the wearer is wearing a full set", () -> {
					this.fillPlayerArmorInventory();
					this.attackEntity(this.zombie, this.player);
					verify(this.zombie, atLeastOnce()).setFire(eq(20));
				}),
				dynamicTest("should set attacker on fire for 5 seconds when attacker is an EntityLivingBase and the wearer is wearing 1 piece", () -> {
					this.setPlayerArmorInventory(new ItemStack(this.helmet));
					this.attackEntity(this.zombie, this.player);
					verify(this.zombie, atLeastOnce()).setFire(eq(5));
				}),
				dynamicTest("should not do anything when attacker is null", () -> {
					this.fillPlayerArmorInventory();
					this.attackEntity(null, this.player);
					// don't have to do anything here; if we did something on a null it would blow up
				}),
				dynamicTest("should not do anything when attacker is already dead", () -> {
					this.fillPlayerArmorInventory();
					this.attackEntity(this.deadZombie, this.player);
					verify(this.deadZombie, never()).setFire(anyInt());
				}),
				dynamicTest("should not do anything when attacker is a projectile", () -> {
					this.fillPlayerArmorInventory();
					this.attackEntity(this.arrow, this.player);
					verify(this.arrow, never()).setFire(anyInt());
				}),
				dynamicTest("should not do anything when attacker is damaged by an ender pearl", () -> {
					this.fillPlayerArmorInventory();
					this.player.attackEntityFrom(DamageSource.causeThrownDamage(this.enderPearl, this.player), 0F);
					verify(this.player, never()).setFire(anyInt());
					verify(this.enderPearl, never()).setFire(anyInt());
				}),
				dynamicTest("should not do anything when the wearer is taking fall damage", () -> {
					this.fillPlayerArmorInventory();
					this.player.attackEntityFrom(DamageSource.FALL, 0F);
				})
		);
	}

	@TestFactory
	public List<DynamicTest> fireArmorPassiveEffectTests() {
		this.initAsFireArmor();
		return Arrays.asList(
				// this test has to be first because of how Mockito works
				dynamicTest("should not grant fire resistance potion effect when wearing less than a full set", () -> {
					this.setPlayerArmorInventory(new ItemStack(this.helmet));
					this.helmet.applyPassiveEffect(this.player);
					verify(this.player, Mockito.never()).addPotionEffect(argThat(matchPotionEffect(FIRE_RESISTANCE)));
				}),
				dynamicTest("should grant fire resistance potion effect when wearing the full set", () -> {
					this.fillPlayerArmorInventory();
					this.helmet.applyPassiveEffect(this.player);
					verify(this.player).addPotionEffect(argThat(matchPotionEffect(FIRE_RESISTANCE)));
				})
		);
	}

	@TestFactory
	public List<DynamicTest> waterArmorPassiveEffectTests() {
		this.initAsWaterArmor();
		return Arrays.asList(
				dynamicTest("should not provide the wearer with infinite air when not when not wearing a full set", () -> {
					this.setPlayerArmorInventory(new ItemStack(this.helmet));
					when(this.player.isInWater()).thenReturn(true);
					this.helmet.applyPassiveEffect(this.player);
					verify(this.player, never()).setAir(anyInt());
				}),
				dynamicTest("should provide depth strider when wearer is wearing a full set and is underwater", () -> {
					when(this.player.isInWater()).thenReturn(true);
					this.fillPlayerArmorInventory();
					// make sure that the player doesn't somehow already have depth strider
					assertEquals(0, EnchantmentHelper.getDepthStriderModifier(this.player));
					// now actually apply the effect
					this.helmet.applyPassiveEffect(this.player);
					assertEquals(3, EnchantmentHelper.getDepthStriderModifier(this.player));
				}),
				dynamicTest("should not apply depth strider when wearer is not wearing a full set of water armor", () -> {
					this.setPlayerArmorInventory(new ItemStack(this.helmet), new ItemStack(this.chestplate), new ItemStack(this.normalLeggings), new ItemStack(this.boots));
					when(this.player.isInWater()).thenReturn(true);
					this.helmet.applyPassiveEffect(this.player);
					assertEquals(0, EnchantmentHelper.getDepthStriderModifier(this.player));
				}),
				dynamicTest("should remove depth strider when wearer is wearing a full set and is out of water", () -> {
					this.fillPlayerArmorInventory();
					// make sure the modifier is applied first
					when(this.player.isInWater()).thenReturn(true);
					this.helmet.applyPassiveEffect(this.player);
					assertEquals(3, EnchantmentHelper.getDepthStriderModifier(this.player));
					// the player has left the water
					when(this.player.isInWater()).thenReturn(false);
					// apply our passive effect
					this.helmet.applyPassiveEffect(this.player);
					assertEquals(0, EnchantmentHelper.getDepthStriderModifier(this.player));
				}),
				dynamicTest("should provide the wearer with infinite air while under water", () -> {
					this.fillPlayerArmorInventory();
					when(this.player.isInWater()).thenReturn(true);
					// apply the effect
					this.helmet.applyPassiveEffect(this.player);
					verify(this.player, atLeastOnce()).setAir(eq(300));
				})
		);
	}

	@TestFactory
	public List<DynamicTest> leafArmorUserHurtTests() {
		this.initAsLeafArmor();
		return Arrays.asList(
				dynamicTest("should damage an attacker if it's undead", () -> {
					this.fillPlayerArmorInventory();
					this.attackEntity(this.zombie, this.player);
					// the player is wearing a full set of armor, each one executes its own onUserHurt method
					verify(this.zombie, times(4)).attackEntityFrom(any(DamageSource.class), eq(16F));
				}),
				dynamicTest("should not damage an attacker if it's already dead", () -> {
					this.fillPlayerArmorInventory();
					this.attackEntity(this.deadZombie, this.player);
					verify(this.deadZombie, never()).attackEntityFrom(any(DamageSource.class), anyFloat());
				}),
				dynamicTest("should not attempt to damage anything if attacker is null", () -> {
					this.fillPlayerArmorInventory();
					this.attackEntity(null, this.player);
					// if an exception is thrown then the test fails, if not, then it passes
				}),
				dynamicTest("should not attempt to attack a projectile", () -> {
					this.fillPlayerArmorInventory();
					this.attackEntity(this.arrow, this.player);
					verify(this.arrow, never()).attackEntityFrom(any(DamageSource.class), anyFloat());
				}),
				dynamicTest("should not attempt to attack a non-undead entity", () -> {
					this.fillPlayerArmorInventory();
					this.attackEntity(this.spider, this.player);
					verify(this.spider, never()).attackEntityFrom(any(DamageSource.class), anyFloat());
				})
		);
	}

	@TestFactory
	public List<DynamicTest> leafArmorPassiveEffectTests() {
		this.initAsLeafArmor();
		return Arrays.asList(
				dynamicTest("should remove wither from the wearer", () -> {
					this.fillPlayerArmorInventory();
					this.helmet.applyPassiveEffect(this.player);
					verify(this.player).removePotionEffect(argThat(matchPotion(MobEffects.WITHER)));
				}),
				dynamicTest("should apply regeneration I when wearer's level <= 10", () -> {
					this.player.experienceLevel = 0;
					this.fillPlayerArmorInventory();
					this.helmet.applyPassiveEffect(this.player);
					verify(this.player, atLeastOnce()).addPotionEffect(argThat(matchPotionOfLevel(REGENERATION, 0)));
				}),
				dynamicTest("should apply regeneration III when wearer's level >= 30", () -> {
					// should result in regen 2
					this.player.experienceLevel = 50;
					this.fillPlayerArmorInventory();
					this.helmet.applyPassiveEffect(this.player);
					verify(this.player, atLeastOnce()).addPotionEffect(argThat(matchPotionOfLevel(REGENERATION, 2)));
				})
		);
	}

	private void initAsFireArmor() {
		this.helmet = new FireArmor(HEAD);
		this.chestplate = new FireArmor(CHEST);
		this.leggings = new FireArmor(LEGS);
		this.boots = new FireArmor(FEET);
	}

	private void initAsWaterArmor() {
		this.helmet = new WaterArmor(HEAD);
		this.chestplate = new WaterArmor(CHEST);
		this.leggings = new WaterArmor(LEGS);
		this.boots = new WaterArmor(FEET);
	}

	private void initAsLeafArmor() {
		this.helmet = new LeafArmor(HEAD);
		this.chestplate = new LeafArmor(CHEST);
		this.leggings = new LeafArmor(LEGS);
		this.boots = new LeafArmor(FEET);
	}

	private void setPlayerArmorInventory(ItemStack... pieces) {
		this.clearPlayerArmorInventory();
		TestHelper.setField(this.playerInventory, "armorInventory", NonNullList.from(new ItemStack(Items.AIR), pieces), InventoryPlayer.class);
	}

	private void clearPlayerArmorInventory() {
		TestHelper.setField(this.playerInventory, "armorInventory", NonNullList.withSize(4, new ItemStack(Items.AIR)), InventoryPlayer.class);
	}

	private void attackEntity(Entity attacker, Entity target) {
		target.attackEntityFrom(new EntityDamageSource(GENERIC.damageType, attacker), 0F);
	}

	private void fillPlayerArmorInventory() {
		this.setPlayerArmorInventory(new ItemStack(this.helmet), new ItemStack(this.chestplate), new ItemStack(this.leggings), new ItemStack(this.boots));
	}
}
