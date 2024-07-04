package it.crystalnest.leathered_boots.handler;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;

/**
 * Handlers register.
 */
public final class HandlerRegistry {
  private HandlerRegistry() {}

  /**
   * Registers all mod handlers.
   */
  public static void register() {
    LootTableEvents.MODIFY.register(LootTableEventsHandler::handle);
  }
}
