package it.crystalnest.leathered_boots.handler;

import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBoots;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Event handler for events fired on the {@link Mod.EventBusSubscriber.Bus#MOD Mod Bus}.
 */
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEventsHandler {
  private ModEventsHandler() {}

  /**
   * Registers the {@link CauldronInteraction}s for each {@link LeatheredBootsItem}.
   *
   * @param event {@link FMLCommonSetupEvent}.
   */
  @SubscribeEvent
  public static void handle(FMLCommonSetupEvent event) {
    for (LeatheredBootsItem boots : LeatheredBoots.getLeatheredBoots()) {
      CauldronInteraction.WATER.map().put(boots, CauldronInteraction.DYED_ITEM);
    }
  }
}
