package elementalitems.blocks.ore;

import elementalitems.ElementalTypes;
import elementalitems.items.ItemHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.BiomeDictionary;

import java.util.Arrays;
import java.util.Random;

public class FireCrystalOre extends BaseOre {

	public static final IProperty<Integer> textureProperty = PropertyInteger.create("nether_variant", 0, 1);

	public FireCrystalOre() {
		super(ElementalTypes.FIRE, Material.ROCK);
		this.blocksThisCanGenerateOver = Arrays.asList(Blocks.STONE, Blocks.NETHERRACK, Blocks.MAGMA);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(BiomeDictionary.Type.HOT, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.NETHER, BiomeDictionary.Type.DRY, BiomeDictionary.Type.HILLS);
		this.biomesToGenerateIn.add(Biomes.HELL);
		this.maxYGeneration = 20;
		this.setLightLevel(1F); // same as glowstone
		// set a varient for the nether so that it generates with a special texture
		this.setDefaultState(this.blockState.getBaseState().withProperty(textureProperty, 0));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(textureProperty);
	}

	@Override
	@SuppressWarnings("deprecation") // this method is required else the game will crash
	public IBlockState getStateFromMeta(int meta) {
		// either 0 or 1
		return this.getDefaultState().withProperty(textureProperty, Math.min(meta, 1));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return (Item) ItemHandler.fireCrystal;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, textureProperty);
	}
}
