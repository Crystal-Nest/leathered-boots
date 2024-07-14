package it.crystalnest.leathered_boots.handler;

import it.crystalnest.leathered_boots.Constants;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.Map;

/**
 * {@link VillagerTradesEvent} handler.
 */
@EventBusSubscriber(modid = Constants.MOD_ID)
public final class VillagerTradesEventHandler {
  private VillagerTradesEventHandler() {}

  /**
   * Adds trades for some leathered boots.
   *
   * @param event {@link VillagerTradesEvent}.
   */
  @SubscribeEvent
  public static void handle(VillagerTradesEvent event) {
    if (event.getType() == VillagerProfession.LEATHERWORKER) {
      for (Map.Entry<Integer, MerchantOffer> trade : Constants.LEATHERED_BOOTS_TRADES.get().entrySet()) {
        event.getTrades().get(trade.getKey().intValue()).add((trader, rand) -> trade.getValue());
      }
    }
  }
}
