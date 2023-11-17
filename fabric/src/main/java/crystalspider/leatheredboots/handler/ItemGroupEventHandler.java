package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.api.LeatheredBoots;
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
    entries.addAll(LeatheredBoots.getLeatheredBootsStack());
  }
}
