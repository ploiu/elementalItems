package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;
import ploiu.elementalitems.util.EntityUtils;

public class EntityIceArrow extends BaseEntityArrow {
	public EntityIceArrow(World worldIn, double x, double y, double z) {
		super(ElementalItemsEntityRegistry.iceArrowEntity, worldIn, x, y, z, ElementalTypes.ICE);
	}

	public EntityIceArrow(EntityType<? extends BaseEntityArrow> entityType, World world) {
		super(entityType, world, ElementalTypes.ICE);
	}

	public EntityIceArrow(World worldIn, LivingEntity shooter) {
		super(ElementalItemsEntityRegistry.iceArrowEntity, worldIn, shooter, ElementalTypes.ICE);
	}

	@Override
	public void hitEntity(EntityRayTraceResult rayTraceResult) {
		// slow and weaken the entity briefly
		if(EntityUtils.isValidLivingEntity(rayTraceResult.getEntity())) {
			LivingEntity target = (LivingEntity) rayTraceResult.getEntity();
			// slow and weaken the target
			target.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 2, false, true));
			target.addEffect(new EffectInstance(Effects.WEAKNESS, 100, 2, false, true));
		}
	}

	@Override
	public void hitBlock(BlockRayTraceResult rayTraceResult) {
		// TODO maybe make it freeze water
	}
}
