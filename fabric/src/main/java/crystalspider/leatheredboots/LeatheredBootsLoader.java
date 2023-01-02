package crystalspider.leatheredboots;

import crystalspider.leatheredboots.armor.LeatheredArmorMaterials;
import crystalspider.leatheredboots.handlers.ItemGroupEventHandler;
import crystalspider.leatheredboots.handlers.LootTableEventsHandler;
import crystalspider.leatheredboots.items.LeatheredBootsItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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

  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_CHAIN Chain}.
   */
  public static final LeatheredBootsItem CHAINMAIL_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_CHAIN, false);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_IRON Iron}.
   */
  public static final LeatheredBootsItem IRON_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_IRON, false);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_GOLD Gold}.
   */
  public static final LeatheredBootsItem GOLDEN_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_GOLD, false);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_DIAMOND Diamond}.
   */
  public static final LeatheredBootsItem DIAMOND_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_DIAMOND, false);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#LEATHERED_NETHERITE Netherite}.
   */
  public static final LeatheredBootsItem NETHERITE_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.LEATHERED_NETHERITE, true);
  /**
   * {@link LeatheredBootsItem} made of {@link LeatheredArmorMaterials#ENDERITE Enderite}.
   */
  // public static final LeatheredBootsItem ENDERITE_LEATHERED_BOOTS = new LeatheredBootsItem(LeatheredArmorMaterials.ENDERITE, true);

  /**
   * {@link ItemGroup} for {@link LeatheredBootsItem Leathered Boots Items}.
   */
  public static final ItemGroup LEATHERED_BOOTS_GROUP = FabricItemGroup
    .builder(new Identifier(LeatheredBootsLoader.MODID, LeatheredBootsLoader.LEATHERED_BOOTS_ID))
    .icon(() -> NETHERITE_LEATHERED_BOOTS.getDefaultStack())
    .build();

  @Override
	public void onInitialize() {
    registerItems();
    registerBehaviors();
    registerTrades();
    registerHandlers();
  }

  /**
   * Registers items.
   */
  private void registerItems() {
    Registry.register(Registries.ITEM, new Identifier(MODID, "chainmail_" + LEATHERED_BOOTS_ID), CHAINMAIL_LEATHERED_BOOTS);
    Registry.register(Registries.ITEM, new Identifier(MODID, "iron_" + LEATHERED_BOOTS_ID), IRON_LEATHERED_BOOTS);
    Registry.register(Registries.ITEM, new Identifier(MODID, "diamond_" + LEATHERED_BOOTS_ID), DIAMOND_LEATHERED_BOOTS);
    Registry.register(Registries.ITEM, new Identifier(MODID, "golden_" + LEATHERED_BOOTS_ID), GOLDEN_LEATHERED_BOOTS);
    Registry.register(Registries.ITEM, new Identifier(MODID, "netherite_" + LEATHERED_BOOTS_ID), NETHERITE_LEATHERED_BOOTS);
    // Registry.register(Registries.ITEM, new Identifier(MODID, "enderite_" + LEATHERED_BOOTS_ID), ENDERITE_LEATHERED_BOOTS);
  }

  /**
   * Register behaviors.
   */
  private void registerBehaviors() {
    CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(CHAINMAIL_LEATHERED_BOOTS, CauldronBehavior.CLEAN_DYEABLE_ITEM);
    CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(IRON_LEATHERED_BOOTS, CauldronBehavior.CLEAN_DYEABLE_ITEM);
    CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(DIAMOND_LEATHERED_BOOTS, CauldronBehavior.CLEAN_DYEABLE_ITEM);
    CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(GOLDEN_LEATHERED_BOOTS, CauldronBehavior.CLEAN_DYEABLE_ITEM);
    CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(NETHERITE_LEATHERED_BOOTS, CauldronBehavior.CLEAN_DYEABLE_ITEM);
    // CauldronBehavior.WATER_CAULDRON_BEHAVIOR.put(ENDERITE_LEATHERED_BOOTS, CauldronBehavior.CLEAN_DYEABLE_ITEM);
  }

  /**
   * Register villager trades.
   */
  private void registerTrades() {
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 3, factories -> factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 4), CHAINMAIL_LEATHERED_BOOTS.getDefaultStack(), 5, 6, 0.02F)));
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 4, factories -> factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 9), IRON_LEATHERED_BOOTS.getDefaultStack(), 3, 10, 0.02F)));
    TradeOfferHelper.registerVillagerOffers(VillagerProfession.LEATHERWORKER, 5, factories -> factories.add((entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, 13), DIAMOND_LEATHERED_BOOTS.getDefaultStack(), 1, 30, 0.02F)));
  }

  /**
   * Register event handlers.
   */
  private void registerHandlers() {
    LootTableEvents.MODIFY.register(LootTableEventsHandler::handle);
    ItemGroupEvents.modifyEntriesEvent(LEATHERED_BOOTS_GROUP).register(ItemGroupEventHandler::handle);
  }
}