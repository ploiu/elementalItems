package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;
import ploiu.elementalitems.util.ElementalUtils;

public class EntityEarthArrow extends BaseEntityArrow {
	public EntityEarthArrow(World worldIn, double x, double y, double z) {
		super(ElementalItemsEntityRegistry.earthArrowEntity, worldIn, x, y, z, ElementalTypes.EARTH);
	}

	public EntityEarthArrow(EntityType<? extends BaseEntityArrow> entityType, World world) {
		super(entityType, world, ElementalTypes.EARTH);
	}

	public EntityEarthArrow(World worldIn, LivingEntity shooter, ElementalTypes type) {
		super(ElementalItemsEntityRegistry.earthArrowEntity, worldIn, shooter, type);
	}

	@Override
	public void hitEntity(EntityRayTraceResult rayTraceResult) {
		// TODO
	}

	@Override
	public void hitBlock(BlockRayTraceResult rayTraceResult) {
		// no-op or maybe TODO sink the block into the ground because the arrow is heavy
	}

}
