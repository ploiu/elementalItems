package ploiu.elementalitems.events;

import net.minecraft.block.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ploiu.elementalitems.blocks.ElementalItemsBlockRegistry;

@Mod.EventBusSubscriber(modid = "elementalitems")
public class BlockEvents {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		BlockState state = event.getState();
		if(state != null && state.equals(ElementalItemsBlockRegistry.netherCrystalOre.defaultBlockState())) {
			// TODO nether crystal ore
		}
	}
}
