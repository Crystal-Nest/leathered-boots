package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import crystalspider.leatheredboots.item.ItemRegistry;
import crystalspider.leatheredboots.item.LeatheredBootsItem;
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
    CauldronInteraction.WATER.put(ItemRegistry.CHAINMAIL_LEATHERED_BOOTS.get(), CauldronInteraction.DYED_ITEM);
    CauldronInteraction.WATER.put(ItemRegistry.IRON_LEATHERED_BOOTS.get(), CauldronInteraction.DYED_ITEM);
    CauldronInteraction.WATER.put(ItemRegistry.GOLDEN_LEATHERED_BOOTS.get(), CauldronInteraction.DYED_ITEM);
    CauldronInteraction.WATER.put(ItemRegistry.DIAMOND_LEATHERED_BOOTS.get(), CauldronInteraction.DYED_ITEM);
    CauldronInteraction.WATER.put(ItemRegistry.NETHERITE_LEATHERED_BOOTS.get(), CauldronInteraction.DYED_ITEM);
  }
}
