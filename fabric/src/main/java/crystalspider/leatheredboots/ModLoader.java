package crystalspider.leatheredboots;

import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.api.RegisterProvider;
import crystalspider.leatheredboots.enchantment.EnchantmentRegistry;
import crystalspider.leatheredboots.handler.HandlerRegistry;
import crystalspider.leatheredboots.item.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

/**
 * Leathered Boots mod loader.
 */
public class ModLoader implements ModInitializer {
  /**
   * ID of this mod.
   */
  public static final String MOD_ID = "leatheredboots";

  /**
   * {@link RegisterProvider}.
   */
  public static final RegisterProvider REGISTER_PROVIDER = new RegisterProvider(MOD_ID);

  @Override
	public void onInitialize() {
    ItemRegistry.register();
    EnchantmentRegistry.register();
    HandlerRegistry.register();
    registerTrades();
  }

  /**
   * Register villager trades.
   */
  private void registerTrades() {
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 3, factories -> factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 4), LeatheredBoots.getLeatheredBootsStack(LeatheredArmorMaterial.LEATHERED_CHAIN), 5, 6, 0.02F)));
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 4, factories -> factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 9), LeatheredBoots.getLeatheredBootsStack(LeatheredArmorMaterial.LEATHERED_IRON), 3, 10, 0.02F)));
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 5, factories -> factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 13), LeatheredBoots.getLeatheredBootsStack(LeatheredArmorMaterial.LEATHERED_DIAMOND), 1, 30, 0.02F)));
  }
}
