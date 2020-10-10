package ploiu.elementalitems.entity.arrow;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;
import ploiu.elementalitems.items.combat.ElementalEffects;
import ploiu.elementalitems.util.EntityUtils;

public class EntityFireArrow extends BaseEntityArrow {
	public EntityFireArrow(World worldIn, double x, double y, double z) {
		super(ElementalItemsEntityRegistry.fireArrowEntity, worldIn, x, y, z, ElementalTypes.FIRE);
	}

	public EntityFireArrow(EntityType<? extends BaseEntityArrow> entityType, World world) {
		super(entityType, world, ElementalTypes.FIRE);
	}

	public EntityFireArrow(World worldIn, LivingEntity shooter) {
		super(ElementalItemsEntityRegistry.fireArrowEntity, worldIn, shooter, ElementalTypes.FIRE);
	}

	@Override
	public void hitEntity(EntityRayTraceResult rayTraceResult) {
		// set the entity on fire if it's still valid
		if(!this.inWater && EntityUtils.isValidLivingEntity(rayTraceResult.getEntity())) {
			rayTraceResult.getEntity().setFire(5);
		}
	}

	@Override
	public void hitBlock(BlockRayTraceResult rayTraceResult) {
		if(!this.inWater) {
			BlockPos positionHit = rayTraceResult.getPos();
			BlockState fire = Blocks.FIRE.getDefaultState();
			// set the block this is on on fire
			BlockPos blockPosForFire = positionHit.offset(rayTraceResult.getFace());
			this.world.setBlockState(blockPosForFire, fire, 11);
		}
	}
}
