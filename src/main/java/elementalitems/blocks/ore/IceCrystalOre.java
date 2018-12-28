package elementalitems.blocks.ore;

import elementalitems.ElementalTypes;
import elementalitems.items.ItemHandler;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Random;

public class IceCrystalOre extends BaseOre {

	public IceCrystalOre() {
		super(ElementalTypes.ICE, Material.ICE);
		this.blocksThisCanGenerateOver = Arrays.asList(Blocks.ICE, Blocks.PACKED_ICE);
		this.biomesToGenerateIn = this.getBiomesToGenerateInFromTypes(BiomeDictionary.Type.COLD, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.BEACH);
		this.maxVeinSize = 10;
		this.spawnChances = 100;
		this.setHardness(0.5F);
		this.setLightOpacity(3);
		this.setSoundType(SoundType.GLASS);
		this.maxYGeneration = 255;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random random) {
		return 2 + random.nextInt(3);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return (Item) ItemHandler.iceCrystal;
	}

	/**
	 * Get the quantity dropped based on the given fortune level
	 */
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
	}

	@Override
	public float getSlipperiness(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable Entity entity) {
		return 0.98F;
	}
}
