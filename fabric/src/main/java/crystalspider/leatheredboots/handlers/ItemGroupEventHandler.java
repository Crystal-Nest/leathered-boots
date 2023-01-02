package crystalspider.leatheredboots.handlers;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

/**
 * {@link ItemGroupEvents} event handler.
 */
public class ItemGroupEventHandler {
  /**
   * Adds the leathered boots to the creative mode tab.
   * 
   * @param entries
   */
  public static void handle(FabricItemGroupEntries entries) {
    entries.add(LeatheredBootsLoader.CHAINMAIL_LEATHERED_BOOTS);
    entries.add(LeatheredBootsLoader.IRON_LEATHERED_BOOTS);
    entries.add(LeatheredBootsLoader.GOLDEN_LEATHERED_BOOTS);
    entries.add(LeatheredBootsLoader.DIAMOND_LEATHERED_BOOTS);
    entries.add(LeatheredBootsLoader.NETHERITE_LEATHERED_BOOTS);
  }
}
