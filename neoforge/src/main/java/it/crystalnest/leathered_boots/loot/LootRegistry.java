package it.crystalnest.leathered_boots.loot;

import com.mojang.serialization.Codec;
import it.crystalnest.leathered_boots.Constants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/**
 * Registry handler for loot related stuff.
 */
public class LootRegistry {
  /**
   * {@link Codec<? extends IGlobalLootModifier>} {@link DeferredRegister}.
   */
  private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Constants.MOD_ID);

  /**
   * {@link DeferredHolder} for {@link Codec} of {@link ChestLootModifier}.
   */
  public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<ChestLootModifier>> CHEST_LOOT_MODIFIER = LOOT_MODIFIERS.register("chest_loot_modifier", ChestLootModifier.CODEC);

  /**
   * {@link DeferredHolder} for {@link Codec} of {@link ArchaeologyLootModifier}.
   */
  public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<ArchaeologyLootModifier>> ARCHAEOLOGY_LOOT_MODIFIER = LOOT_MODIFIERS.register("archaeology_loot_modifier", ArchaeologyLootModifier.CODEC);

  /**
   * Registers all loot related stuff.
   */
  public static void register(IEventBus bus) {
    LOOT_MODIFIERS.register(bus);
  }
}
