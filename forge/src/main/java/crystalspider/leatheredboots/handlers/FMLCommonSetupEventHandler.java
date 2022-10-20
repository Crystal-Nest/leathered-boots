package crystalspider.leatheredboots.handlers;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import crystalspider.leatheredboots.items.LeatheredBootsItem;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * {@link FMLCommonSetupEvent} handler.
 */
@EventBusSubscriber(modid = LeatheredBootsLoader.MODID, bus = Bus.MOD)
public class FMLCommonSetupEventHandler {
  /**
   * Registers the {@link CauldronInteraction}s for each {@link LeatheredBootsItem}.
   * 
   * @param event
   */
  @SubscribeEvent
  public static void handle(FMLCommonSetupEvent event) {
    CauldronInteraction.WATER.put(LeatheredBootsLoader.CHAINMAIL_LEATHERED_BOOTS.get(), CauldronInteraction.DYED_ITEM);
    CauldronInteraction.WATER.put(LeatheredBootsLoader.IRON_LEATHERED_BOOTS.get(), CauldronInteraction.DYED_ITEM);
    CauldronInteraction.WATER.put(LeatheredBootsLoader.GOLDEN_LEATHERED_BOOTS.get(), CauldronInteraction.DYED_ITEM);
    CauldronInteraction.WATER.put(LeatheredBootsLoader.DIAMOND_LEATHERED_BOOTS.get(), CauldronInteraction.DYED_ITEM);
    CauldronInteraction.WATER.put(LeatheredBootsLoader.NETHERITE_LEATHERED_BOOTS.get(), CauldronInteraction.DYED_ITEM);
  }
}
