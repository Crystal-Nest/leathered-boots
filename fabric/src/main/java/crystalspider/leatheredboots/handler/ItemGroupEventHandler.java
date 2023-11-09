package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.item.ItemRegistry;
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
    entries.add(ItemRegistry.CHAINMAIL_LEATHERED_BOOTS);
    entries.add(ItemRegistry.IRON_LEATHERED_BOOTS);
    entries.add(ItemRegistry.GOLDEN_LEATHERED_BOOTS);
    entries.add(ItemRegistry.DIAMOND_LEATHERED_BOOTS);
    entries.add(ItemRegistry.NETHERITE_LEATHERED_BOOTS);
  }
}
