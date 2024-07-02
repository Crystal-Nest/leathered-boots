package it.crystalnest.leathered_boots;

import it.crystalnest.leathered_boots.api.LeatheredBoots;
import it.crystalnest.leathered_boots.handler.HandlerRegistry;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.ApiStatus;

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
   * Register villager trades.
   */
  private void registerTrades() {
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 3, factories -> factories.add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 4), LeatheredBoots.getLeatheredBootsStack(Constants.MOD_ID, LeatheredArmorMaterial.LEATHERED_CHAIN), 5, 6, 0.02F)));
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 4, factories -> factories.add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 9), LeatheredBoots.getLeatheredBootsStack(Constants.MOD_ID, LeatheredArmorMaterial.LEATHERED_IRON), 3, 10, 0.02F)));
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 5, factories -> factories.add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 13), LeatheredBoots.getLeatheredBootsStack(Constants.MOD_ID, LeatheredArmorMaterial.LEATHERED_DIAMOND), 1, 30, 0.02F)));
  }
}
