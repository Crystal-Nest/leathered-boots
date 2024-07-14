package it.crystalnest.leathered_boots.handler;

import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Event handler for events fired on the {@link EventBusSubscriber.Bus#MOD Mod Bus}.
 */
@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class ModEventsHandler {
  private ModEventsHandler() {}

  /**
   * Registers the {@link CauldronInteraction}s for each {@link LeatheredBootsItem}.
   *
   * @param event {@link FMLCommonSetupEvent}.
   */
  @SubscribeEvent
  public static void handle(FMLCommonSetupEvent event) {
    for (LeatheredBootsItem boots : LeatheredBootsManager.getBoots()) {
      CauldronInteraction.WATER.map().put(boots, CauldronInteraction.DYED_ITEM);
    }
  }
}
