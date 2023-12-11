package crystalspider.leatheredboots.handler;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;

/**
 * Mod handlers register.
 */
public class HandlerRegistry {
  /**
   * Registers all mod handlers.
   */
  public static final void register() {
    LootTableEvents.MODIFY.register(LootTableEventsHandler::handle);
  }
}
