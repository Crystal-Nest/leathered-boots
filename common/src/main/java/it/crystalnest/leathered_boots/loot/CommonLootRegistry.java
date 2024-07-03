package it.crystalnest.leathered_boots.loot;

import it.crystalnest.cobweb.api.registry.CobwebRegister;
import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.leathered_boots.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.function.Supplier;

/**
 * Common module loot registry.
 */
public final class CommonLootRegistry {
  /**
   * {@link CobwebRegister} for {@link LootItemConditionType}s.
   */
  private static final CobwebRegister<LootItemConditionType> LOOT_ITEM_CONDITION_TYPES = CobwebRegistry.of(Registries.LOOT_CONDITION_TYPE, Constants.MOD_ID);

  /**
   * Biomes check {@link LootItemConditionType}.
   */
  public static final Supplier<LootItemConditionType> BIOMES_CHECK = LOOT_ITEM_CONDITION_TYPES.register("biomes_check", () -> new LootItemConditionType(new BiomesCheck.Serializer()));

  private CommonLootRegistry() {}

  /**
   * Called to load the class and register.
   */
  public static void register() {}
}
