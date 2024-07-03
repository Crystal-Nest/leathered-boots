package it.crystalnest.leathered_boots.loot;

import com.mojang.serialization.Codec;
import it.crystalnest.leathered_boots.Constants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/**
 * Registry for loot modifiers.
 */
public final class LootRegistry {
  /**
   * {@link DeferredRegister} for {@link IGlobalLootModifier}s.
   */
  private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Constants.MOD_ID);

  static {
    LOOT_MODIFIERS.register("chest_loot_modifier", ChestLootModifier.CODEC);
    LOOT_MODIFIERS.register("archaeology_loot_modifier", ArchaeologyLootModifier.CODEC);
  }

  private LootRegistry() {}

  /**
   * Registers all loot modifiers.
   *
   * @param bus {@link IEventBus}.
   */
  public static void register(IEventBus bus) {
    LOOT_MODIFIERS.register(bus);
  }
}
