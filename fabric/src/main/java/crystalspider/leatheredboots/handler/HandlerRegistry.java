package crystalspider.leatheredboots.handler;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.enchantment.Enchantment;

/**
 * Mod {@link Enchantment} register.
 */
public class HandlerRegistry {
  public static final void register() {
    LootTableEvents.MODIFY.register(LootTableEventsHandler::handle);
  }
}
