package crystalspider.leatheredboots;

import crystalspider.leatheredboots.item.ItemRegistry;
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
      ItemRegistry.CHAINMAIL_LEATHERED_BOOTS,
      ItemRegistry.IRON_LEATHERED_BOOTS,
      ItemRegistry.DIAMOND_LEATHERED_BOOTS,
      ItemRegistry.GOLDEN_LEATHERED_BOOTS,
      ItemRegistry.NETHERITE_LEATHERED_BOOTS
    );
  }
}
