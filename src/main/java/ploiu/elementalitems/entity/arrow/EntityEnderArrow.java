package ploiu.elementalitems.entity.arrow;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;
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
		if(this.level.isClientSide && EntityUtils.isValidLivingEntity(this.getOwner())) {
			EntityTeleportEvent.EnderEntity event = new EntityTeleportEvent.EnderEntity((LivingEntity) this.getOwner(), this.getX(), this.getY(), this.getZ());
			this.getOwner().teleportToWithTicket(event.getTargetX(), event.getTargetY(), event.getTargetZ());
			// play the sounds
			this.level.playSound(null, this.getOwner().getX(), this.getOwner().getY(), this.getOwner().getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
			this.getOwner().playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
		}
	}
}
