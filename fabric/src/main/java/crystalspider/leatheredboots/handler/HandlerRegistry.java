package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.item.ItemRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;

public class HandlerRegistry {
  public static final void register() {
    LootTableEvents.MODIFY.register(LootTableEventsHandler::handle);
    ItemGroupEvents.modifyEntriesEvent(ItemRegistry.LEATHERED_BOOTS_GROUP).register(ItemGroupEventHandler::handle);
  }
}
