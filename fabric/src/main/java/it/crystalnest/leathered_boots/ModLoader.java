package it.crystalnest.leathered_boots;

import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import it.crystalnest.leathered_boots.handler.HandlerRegistry;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.ApiStatus;

import java.util.Map;

/**
 * Mod loader.
 */
@ApiStatus.Internal
public final class ModLoader implements ModInitializer {
  /**
   * Leathered boots tab.<br />
   * Includes all registered leathered boots.
   */
  private static final CreativeModeTab LEATHERED_BOOTS_CREATIVE_TAB = FabricItemGroup.builder(Constants.LEATHERED_BOOTS_TAB_ID)
    .icon(() -> LeatheredBootsManager.getBootsStack(Constants.MOD_ID, LeatheredArmorMaterial.LEATHERED_NETHERITE))
    .displayItems((features, output) -> output.acceptAll(LeatheredBootsManager.getBootsStack()))
    .build();

  @Override
  public void onInitialize() {
    CommonModLoader.init();
    HandlerRegistry.register();
    registerTrades();
  }

  /**
   * Register villager trade offers.
   */
  private static void registerTrades() {
    for (Map.Entry<Integer, MerchantOffer> trade : Constants.LEATHERED_BOOTS_TRADES.get().entrySet()) {
      TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, trade.getKey(), factories -> factories.add((entity, random) -> trade.getValue()));
    }
  }
}
