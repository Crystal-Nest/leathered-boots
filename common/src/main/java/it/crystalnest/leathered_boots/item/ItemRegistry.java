package it.crystalnest.leathered_boots.item;

import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import org.jetbrains.annotations.ApiStatus;

/**
 * Item registry.
 */
@ApiStatus.Internal
public final class ItemRegistry {
  private ItemRegistry() {}

  /**
   * Called to load the class and register.
   */
  public static void register() {
    LeatheredBootsManager.registerBoots(
      Constants.MOD_ID,
      LeatheredArmorMaterial.LEATHERED_CHAIN,
      LeatheredArmorMaterial.LEATHERED_IRON,
      LeatheredArmorMaterial.LEATHERED_GOLD,
      LeatheredArmorMaterial.LEATHERED_DIAMOND
    );
    LeatheredBootsManager.registerBoots(Constants.MOD_ID, true, LeatheredArmorMaterial.LEATHERED_NETHERITE);
  }
}
