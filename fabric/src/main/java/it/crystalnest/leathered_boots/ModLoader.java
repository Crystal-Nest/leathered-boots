package it.crystalnest.leathered_boots;

import it.crystalnest.leathered_boots.handler.HandlerRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;

/**
 * Mod loader.
 */
@ApiStatus.Internal
public final class ModLoader implements ModInitializer {
  @Override
  public void onInitialize() {
    CommonModLoader.init();
    HandlerRegistry.register();
    registerTrades();
  }

  /**
   * Register villager trade offers.
   */
  private void registerTrades() {
    for (Map.Entry<Integer, MerchantOffer> trade : Constants.LEATHERED_BOOTS_TRADES.get().entrySet()) {
      TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, trade.getKey(), factories -> factories.add((entity, random) -> trade.getValue()));
    }
  }
}
