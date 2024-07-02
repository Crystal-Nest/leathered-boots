package it.crystalnest.leathered_boots.handler;

import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBoots;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * {@link RegisterColorHandlersEvent} handler.
 */
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class RegisterColorHandlersEventHandler {
  /**
   * Registers the {@link ItemColor} for {@link LeatheredBootsItem}.
   *
   * @param event {@link RegisterColorHandlersEvent.Item}.
   */
  @SubscribeEvent
  public static void handleItem(RegisterColorHandlersEvent.Item event) {
    event.register(
      (stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack),
      LeatheredBoots.getLeatheredBoots().toArray(LeatheredBootsItem[]::new)
    );
  }
}