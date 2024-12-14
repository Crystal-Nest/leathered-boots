package it.crystalnest.leathered_boots;

import com.google.common.base.Suppliers;
import it.crystalnest.cobweb.api.pack.dynamic.DynamicDataPack;
import it.crystalnest.cobweb.api.pack.dynamic.DynamicTagBuilder;
import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import it.crystalnest.leathered_boots.item.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.ApiStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Common shared constants across all loaders.
 */
@ApiStatus.Internal
public final class Constants {
  /**
   * Mod ID.
   */
  public static final String MOD_ID = "leathered_boots";

  /**
   * ID for leathered boots creative mod tab.
   */
  public static final String LEATHERED_BOOTS_TAB_ID = "leathered_boots_tab";

  /**
   * Leathered boots dynamic datapack.<br />
   * Adds all leathered boots to {@link ItemTags#DYEABLE}, {@link ItemTags#TRIMMABLE_ARMOR}, {@link ItemTags#TRIMMABLE_ARMOR} and {@link ItemTags#FOOT_ARMOR}.
   */
  public static final DynamicDataPack LEATHERED_BOOTS_DYNAMIC_DATA_PACK = (DynamicDataPack) DynamicDataPack
    .named(ResourceLocation.fromNamespaceAndPath(MOD_ID, "leathered_boots"))
    .add(() -> DynamicTagBuilder.of(Registries.ITEM, ItemTags.DYEABLE, ItemTags.TRIMMABLE_ARMOR, ItemTags.FREEZE_IMMUNE_WEARABLES, ItemTags.FOOT_ARMOR).addElements(LeatheredBootsManager.getBoots()));

  /**
   * Leathered boots trade offers.
   */
  public static final Supplier<Map<Integer, MerchantOffer>> LEATHERED_BOOTS_TRADES = Suppliers.memoize(() -> Map.ofEntries(
    Map.entry(3, new MerchantOffer(new ItemCost(Items.EMERALD, 4), ItemRegistry.LEATHERED_CHAIN_BOOTS.get().getDefaultInstance(), 5, 6, 0.02F)),
    Map.entry(4, new MerchantOffer(new ItemCost(Items.EMERALD, 9), ItemRegistry.LEATHERED_IRON_BOOTS.get().getDefaultInstance(), 3, 10, 0.02F)),
    Map.entry(5, new MerchantOffer(new ItemCost(Items.EMERALD, 13), ItemRegistry.LEATHERED_DIAMOND_BOOTS.get().getDefaultInstance(), 1, 30, 0.02F))
  ));

  /**
   * Mod logger.
   */
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  private Constants() {}
}
