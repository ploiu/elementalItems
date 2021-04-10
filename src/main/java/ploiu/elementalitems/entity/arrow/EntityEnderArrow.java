package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import ploiu.elementalitems.ElementalTypes;
import ploiu.elementalitems.entity.ElementalItemsEntityRegistry;
import ploiu.elementalitems.items.combat.ElementalEffects;
import ploiu.elementalitems.util.EntityUtils;

public class EntityEnderArrow extends BaseEntityArrow {
	public EntityEnderArrow(World worldIn, double x, double y, double z) {
		super(ElementalItemsEntityRegistry.enderArrowEntity, worldIn, x, y, z, ElementalTypes.ENDER);
	}

	public EntityEnderArrow(EntityType<? extends BaseEntityArrow> entityType, World world) {
		super(entityType, world, ElementalTypes.ENDER);
	}

	public EntityEnderArrow(World worldIn, LivingEntity shooter) {
		super(ElementalItemsEntityRegistry.enderArrowEntity, worldIn, shooter, ElementalTypes.ENDER);
	}

	@Override
	public void hitEntity(EntityRayTraceResult rayTraceResult) {
		// teleport the target
		if(EntityUtils.isValidLivingEntity(rayTraceResult.getEntity())) {
			ElementalEffects.teleportTarget((LivingEntity) rayTraceResult.getEntity());
		}
	}

	@Override
	public void hitBlock(BlockRayTraceResult rayTraceResult) {
		// if the world is not remote and our shooter is a valid entity, attempt to teleport our shooter to where the arrow landed
		if(!this.world.isRemote && EntityUtils.isValidLivingEntity(this.func_234616_v_())) {
			EnderTeleportEvent event = new EnderTeleportEvent((LivingEntity) this.func_234616_v_(), this.getPosX(), this.getPosY(), this.getPosZ(), 0);
			this.func_234616_v_().setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
			// play the sounds
			this.world.playSound(null, this.func_234616_v_().getPosX(), this.func_234616_v_().getPosY(), this.func_234616_v_().getPosZ(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.func_234616_v_().playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
		}
	}
}
