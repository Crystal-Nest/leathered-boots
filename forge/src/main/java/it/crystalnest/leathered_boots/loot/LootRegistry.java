package it.crystalnest.leathered_boots.loot;

import it.crystalnest.leathered_boots.Constants;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Registry for loot modifiers.
 */
public final class LootRegistry {
  /**
   * {@link DeferredRegister} for {@link IGlobalLootModifier}s.
   */
  private static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, Constants.MOD_ID);

  static {
    LOOT_MODIFIERS.register("chest_loot_modifier", ChestLootModifier.Serializer::new);
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
