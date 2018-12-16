package elementalitems.util;

import elementalitems.TestHelper;
import elementalitems.items.combat.armor.BaseArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Bootstrap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static elementalitems.ElementalType.FIRE;
import static elementalitems.ElementalType.PLAIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EntityUtilsTests {

	// the mock entity we are using to check for isValidEntityLivingBase
	@Mock
	private EntityLivingBase deadEntityLivingBase;
	@Mock
	private EntityLivingBase aliveEntityLivingBase;
	@Mock
	private EntityZombie biomeEntity;
	@Mock
	private EntityPig notInBiomeEntity;
	@Mock
	private Entity entity;
	@Mock
	private Biome biome;
	@Mock
	World world;
	@Mock
	WorldProvider worldProvider;
	@Mock
	EntityPlayer player;
	@Mock
	InventoryPlayer playerInventory;
	@Mock
	Item item;
	@Mock
	BaseArmor plainArmor;
	@Mock
	BaseArmor fireArmor;

	// misc variables
	private int playerXPLevel = 30;
	private BlockPos blockPos = new BlockPos(0, 0, 0);

	// the util we're testing
	private EntityUtils utils = EntityUtils.getInstance();

	static {
		Bootstrap.register();
	}

	@BeforeAll
	public void setup() {
		MockitoAnnotations.initMocks(this);
		// the first 4 calls returns all PLAIN, then the 5th call returns FIRE but then continues to return PLAIN
		when(this.plainArmor.getType()).thenReturn(PLAIN);
		when(this.fireArmor.getType()).thenReturn(FIRE);
		when(this.biome.getSpawnableList(any(EnumCreatureType.class))).thenReturn(Arrays.asList(new Biome.SpawnListEntry(this.biomeEntity.getClass(), 0, 0, 1), new Biome.SpawnListEntry(EntityBlaze.class, 0, 0, 1)));
		when(this.player.isEntityAlive()).thenReturn(true);
		when(this.aliveEntityLivingBase.isEntityAlive()).thenReturn(true);
		when(this.player.getArmorInventoryList()).thenCallRealMethod();


		// since there's not a getter associated with the world object, we have to use reflection
		TestHelper.setField(this.world, "provider", this.worldProvider, World.class);
		TestHelper.setField(this.player, "inventory", this.playerInventory, EntityPlayer.class);
		TestHelper.setField(this.player, "experienceLevel", this.playerXPLevel, EntityPlayer.class);
	}

	@TestFactory
	List<DynamicTest> isValidEntityLivingBaseTests() {
		return Arrays.asList(
				dynamicTest("should return true when given a living EntityLivingBase", () -> assertTrue(this.utils.isValidEntityLivingBase(this.aliveEntityLivingBase))),
				dynamicTest("should return false when given a non-living EntityLivingBase", () -> assertFalse(this.utils.isValidEntityLivingBase(this.deadEntityLivingBase))),
				dynamicTest("should return false when given a non EntityLivingBase", () -> assertFalse(this.utils.isValidEntityLivingBase(this.entity))),
				dynamicTest("should return false when given null", () -> assertFalse(this.utils.isValidEntityLivingBase(null)))
		);
	}

	@TestFactory
	List<DynamicTest> isMobFromBiome() {
		return Arrays.asList(
				dynamicTest("should return true when given an entity from the biome", () -> assertTrue(this.utils.isMobFromBiome(this.biomeEntity, this.biome))),
				dynamicTest("should return false when given an entity not from the biome", () -> assertFalse(this.utils.isMobFromBiome(this.notInBiomeEntity, this.biome))),
				dynamicTest("should return false when given a null entity", () -> assertFalse(this.utils.isMobFromBiome(null, this.biome))),
				dynamicTest("should return false when given a null biome", () -> assertFalse(this.utils.isMobFromBiome(this.biomeEntity, null)))
		);
	}

	@TestFactory
	List<DynamicTest> doesEntityHaveFullElementalSetOfType() {
		return Arrays.asList(
				dynamicTest("should return true when given 4 BaseArmor of the same type", () -> {
					TestHelper.setField(this.playerInventory, "armorInventory", NonNullList.from(new ItemStack(this.plainArmor)), InventoryPlayer.class);
					assertTrue(this.utils.doesEntityHaveFullElementalSetOfType(this.player, PLAIN));
				}),
				dynamicTest("should return false when given a BaseArmor with a different type than the others", () -> {
					TestHelper.setField(this.playerInventory, "armorInventory", NonNullList.from(new ItemStack(this.plainArmor), new ItemStack(this.fireArmor)), InventoryPlayer.class);
					assertFalse(this.utils.doesEntityHaveFullElementalSetOfType(this.player, PLAIN));
				}),
				dynamicTest("should return false when given a non-BaseArmor item", () -> {
					TestHelper.setField(this.playerInventory, "armorInventory", NonNullList.from(new ItemStack(this.plainArmor), new ItemStack(this.item)), InventoryPlayer.class);
					assertFalse(this.utils.doesEntityHaveFullElementalSetOfType(this.player, PLAIN));
				}),
				dynamicTest("should return false when given a null type", () -> assertFalse(this.utils.doesEntityHaveFullElementalSetOfType(this.player, null))),
				dynamicTest("should return false when given a null entity", () -> assertFalse(this.utils.doesEntityHaveFullElementalSetOfType(null, PLAIN)))
		);
	}

	@TestFactory
	List<DynamicTest> getPlayerLevel() {
		return Arrays.asList(
				dynamicTest("should return player level when given a valid player", () -> assertEquals(this.playerXPLevel, this.utils.getPLayerLevel(this.player))),
				dynamicTest("should return 0 when given a non-player entity", () -> assertEquals(0, this.utils.getPLayerLevel(this.entity))),
				dynamicTest("should return 0 when given a null entity", () -> assertEquals(0, this.utils.getPLayerLevel(null)))
		);
	}

	@TestFactory
	List<DynamicTest> spawnXpOrb() {
		return Arrays.asList(
				dynamicTest("should spawn the orb given valid arguments", () -> {
					this.utils.spawnXpOrb(this.world, this.blockPos, 3);
					verify(this.world).spawnEntity(isA(EntityXPOrb.class));
				}),
				dynamicTest("should not spawn the orb given a null BlockPos", () -> {
					this.utils.spawnXpOrb(this.world, null, 3);
					verifyNoMoreInteractions(this.world);
				}),
				dynamicTest("should not spawn the orb given a 0-value xp amount", () -> {
					this.utils.spawnXpOrb(this.world, this.blockPos, 0);
					verifyNoMoreInteractions(this.world);
				})
		);
	}

	// these tests below this comment don't really need a test factory, as the multiple cases are covered in a single call
	@Test
	public void addItemsToPlayerInventoryWithFullInventory() {
		List<ItemStack> expectedItems = Arrays.asList(new ItemStack(this.item), new ItemStack(this.item));
		when(this.player.addItemStackToInventory(any(ItemStack.class))).thenReturn(true).thenReturn(false);
		// the length of the returned list should only be 1
		assertEquals(1, this.utils.addItemsToPlayerInventory(this.player, expectedItems).size());
	}

	@Test
	public void testDropItemsInWorld() {
		// there's no return value to assert, so we have to verify behavior
		this.utils.dropItemsInWorld(this.world, this.player, Collections.singletonList(new ItemStack(this.item)));
		verify(this.world).spawnEntity(any(EntityItem.class));
	}


}
