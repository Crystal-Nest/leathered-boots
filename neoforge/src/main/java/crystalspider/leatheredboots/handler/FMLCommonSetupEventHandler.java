package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.item.LeatheredBootsItem;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.fml.common.Mod.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * {@link FMLCommonSetupEvent} handler.
 */
@EventBusSubscriber(modid = ModLoader.MOD_ID, bus = Bus.MOD)
public final class FMLCommonSetupEventHandler {
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
