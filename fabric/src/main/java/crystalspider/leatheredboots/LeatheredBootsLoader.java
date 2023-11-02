package crystalspider.leatheredboots;

import crystalspider.leatheredboots.api.RegisterProvider;
import crystalspider.leatheredboots.handlers.ItemGroupEventHandler;
import crystalspider.leatheredboots.handlers.LootTableEventsHandler;
import crystalspider.leatheredboots.item.ItemRegistry;
import crystalspider.leatheredboots.item.LeatheredBootsItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

/**
 * Leathered Boots mod loader.
 */
public class LeatheredBootsLoader implements ModInitializer {
  /**
   * ID of this mod.
   */
  public static final String MODID = "leatheredboots";

  /**
   * Base ID for leathered boots.
   */
  public static final String LEATHERED_BOOTS_ID = "leathered_boots";

  public static final RegisterProvider REGISTER_PROVIDER = new RegisterProvider(MODID);

  /**
   * {@link ItemGroup} for {@link LeatheredBootsItem Leathered Boots Items}.
   */
  public static final ItemGroup LEATHERED_BOOTS_GROUP = FabricItemGroup
    .builder(new Identifier(LeatheredBootsLoader.MODID, LeatheredBootsLoader.LEATHERED_BOOTS_ID))
    .icon(() -> ItemRegistry.NETHERITE_LEATHERED_BOOTS.getDefaultStack())
    .build();

  @Override
	public void onInitialize() {
    ItemRegistry.register();
    registerBehaviors();
    registerTrades();
    registerHandlers();
  }

  /**
   * Register behaviors.
   */
  private void registerBehaviors() {
    CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(ItemRegistry.CHAINMAIL_LEATHERED_BOOTS, CauldronBehavior.CLEAN_DYEABLE_ITEM);
    CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(ItemRegistry.IRON_LEATHERED_BOOTS, CauldronBehavior.CLEAN_DYEABLE_ITEM);
    CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(ItemRegistry.DIAMOND_LEATHERED_BOOTS, CauldronBehavior.CLEAN_DYEABLE_ITEM);
    CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(ItemRegistry.GOLDEN_LEATHERED_BOOTS, CauldronBehavior.CLEAN_DYEABLE_ITEM);
    CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(ItemRegistry.NETHERITE_LEATHERED_BOOTS, CauldronBehavior.CLEAN_DYEABLE_ITEM);
  }

  /**
   * Register villager trades.
   */
  private void registerTrades() {
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 3, factories -> factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 4), ItemRegistry.CHAINMAIL_LEATHERED_BOOTS.getDefaultStack(), 5, 6, 0.02F)));
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 4, factories -> factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 9), ItemRegistry.IRON_LEATHERED_BOOTS.getDefaultStack(), 3, 10, 0.02F)));
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 5, factories -> factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 13), ItemRegistry.DIAMOND_LEATHERED_BOOTS.getDefaultStack(), 1, 30, 0.02F)));
  }

  /**
   * Register event handlers.
   */
  private void registerHandlers() {
    LootTableEvents.MODIFY.register(LootTableEventsHandler::handle);
    ItemGroupEvents.modifyEntriesEvent(LEATHERED_BOOTS_GROUP).register(ItemGroupEventHandler::handle);
  }
}
