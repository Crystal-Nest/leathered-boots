package crystalspider.leatheredboots.loot;

import java.util.HashMap;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public class IglooLootModifier extends AbstractLootModifier {
  protected IglooLootModifier(LootItemCondition[] conditionsIn, HashMap<ItemStack, Float> additions) {
    super(conditionsIn, additions);
  }

  /**
   * {@link IglooLootModifier} Serializer.
   */
  public static class Serializer extends AbstractLootModifier.Serializer {
    public Serializer() {
      super(IglooLootModifier::new);
    }
  }
}
