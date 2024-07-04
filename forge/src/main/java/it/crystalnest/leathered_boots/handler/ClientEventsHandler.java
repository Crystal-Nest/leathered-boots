package it.crystalnest.leathered_boots.handler;

import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Client events handler.
 */
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientEventsHandler {
  private ClientEventsHandler() {}

  /**
   * Registers the {@link ItemColor} for all {@link LeatheredBootsItem}s.
   *
   * @param event {@link ColorHandlerEvent.Item}.
   */
  @SubscribeEvent
  public static void handleItem(ColorHandlerEvent.Item event) {
    event.getItemColors().register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), LeatheredBootsManager.getBoots().toArray(LeatheredBootsItem[]::new));
  }
}
