package crystalspider.leatheredboots;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.DyeableItem;

/**
 * Leathered Boots mod client loader.
 */
public class LeatheredBootsClientLoader implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    ColorProviderRegistry.ITEM.register(
      (stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack),
      LeatheredBootsLoader.CHAINMAIL_LEATHERED_BOOTS,
      LeatheredBootsLoader.IRON_LEATHERED_BOOTS,
      LeatheredBootsLoader.DIAMOND_LEATHERED_BOOTS,
      LeatheredBootsLoader.GOLDEN_LEATHERED_BOOTS,
      LeatheredBootsLoader.NETHERITE_LEATHERED_BOOTS
    );
  }
}
