package it.crystalnest.leathered_boots;

import it.crystalnest.leathered_boots.enchantment.EnchantmentRegistry;
import it.crystalnest.leathered_boots.item.ItemRegistry;
import it.crystalnest.leathered_boots.loot.CommonLootRegistry;
import org.jetbrains.annotations.ApiStatus;

/**
 * Common mod loader.
 */
@ApiStatus.Internal
public final class CommonModLoader {
  private CommonModLoader() {}

  /**
   * Initialize common operations across loaders.
   */
  public static void init() {
    ItemRegistry.register();
    EnchantmentRegistry.register();
    CommonLootRegistry.register();
    Constants.LEATHERED_BOOTS_DYNAMIC_DATA_PACK.register();
  }
}
