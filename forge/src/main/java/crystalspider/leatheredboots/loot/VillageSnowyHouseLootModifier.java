package crystalspider.leatheredboots.loot;

import java.util.HashMap;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class VillageSnowyHouseLootModifier extends AbstractLootModifier {
  protected VillageSnowyHouseLootModifier(LootItemCondition[] conditionsIn, HashMap<ItemStack, Float> additions) {
    super(conditionsIn, additions);
  }

  /**
   * {@link VillageSnowyHouseLootModifier} Serializer.
   */
  public static class Serializer extends AbstractLootModifier.Serializer {
    public Serializer() {
      super(VillageSnowyHouseLootModifier::new);
    }
  }
}
