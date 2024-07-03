package it.crystalnest.leathered_boots.handler;

import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.DyeableLeatherItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

/**
 * Client events handler.
 */
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientEventsHandler {
  private ClientEventsHandler() {}

  /**
   * Registers the {@link ItemColor} for all {@link LeatheredBootsItem}s.
   *
   * @param event {@link RegisterColorHandlersEvent.Item}.
   */
  @SubscribeEvent
  public static void handleItem(RegisterColorHandlersEvent.Item event) {
    event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), LeatheredBootsManager.getLeatheredBoots().toArray(LeatheredBootsItem[]::new));
  }
}
