package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;

public class ItemRegistry {
  /**
   * Registers all mod items.
   */
  public static void register() {
    LeatheredBoots.registerLeatheredBoots(
      ModLoader.MOD_ID,
      LeatheredArmorMaterial.LEATHERED_CHAIN,
      LeatheredArmorMaterial.LEATHERED_IRON,
      LeatheredArmorMaterial.LEATHERED_GOLD,
      LeatheredArmorMaterial.LEATHERED_DIAMOND
    );
    LeatheredBoots.registerLeatheredBoots(ModLoader.MOD_ID, true, LeatheredArmorMaterial.LEATHERED_NETHERITE);
  }
}
