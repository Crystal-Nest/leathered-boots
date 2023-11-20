package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.item.LeatheredBootsItem;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * {@link FMLCommonSetupEvent} handler.
 */
@EventBusSubscriber(modid = ModLoader.MOD_ID, bus = Bus.MOD)
public class FMLCommonSetupEventHandler {
  /**
   * Registers the {@link CauldronInteraction}s for each {@link LeatheredBootsItem}.
   * 
   * @param event
   */
  @SubscribeEvent
  public static void handle(FMLCommonSetupEvent event) {
    for (LeatheredBootsItem boots : LeatheredBoots.getLeatheredBoots()) {
      CauldronInteraction.WATER.put(boots, CauldronInteraction.DYED_ITEM);
    }
  }
}
