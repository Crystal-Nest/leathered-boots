package it.crystalnest.leathered_boots.item;

import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import it.crystalnest.leathered_boots.platform.Services;
import net.minecraft.world.item.CreativeModeTab;
import org.jetbrains.annotations.ApiStatus;

/**
 * Item registry.
 */
@ApiStatus.Internal
public final class ItemRegistry {
  /**
   * Leathered boots tab.<br />
   * Includes all registered leathered boots.
   */
  public static final CreativeModeTab LEATHERED_BOOTS_CREATIVE_TAB = Services.ITEM.buildTab(Constants.LEATHERED_BOOTS_TAB_ID, () -> LeatheredBootsManager.getBootsStack(Constants.MOD_ID, LeatheredArmorMaterial.LEATHERED_NETHERITE));

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
