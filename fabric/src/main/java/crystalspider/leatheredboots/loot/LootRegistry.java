package crystalspider.leatheredboots.loot;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.Register;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.registry.Registries;

/**
 * Mod loot related stuff register.
 */
public class LootRegistry {
  /**
   * {@link Register} for {@link LootConditionType}s.
   */
  private static final Register<LootConditionType> REGISTER = ModLoader.REGISTER_PROVIDER.of(Registries.LOOT_CONDITION_TYPE);

  /**
   * {@link LootConditionType} for {@link BiomesCheckLootCondition}.
   */
  public static final LootConditionType BIOMES_CHECK = new LootConditionType(new BiomesCheckLootCondition.Serializer());

  /**
   * Registers all mod loot related stuff.
   */
  public static final void register() {
    REGISTER.apply("biomes_check", BIOMES_CHECK);
  }
}
