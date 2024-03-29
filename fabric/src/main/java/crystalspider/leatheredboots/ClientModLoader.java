package crystalspider.leatheredboots;

import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.item.LeatheredBootsItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.DyeableItem;

/**
 * Leathered Boots mod client loader.
 */
public class ClientModLoader implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    ColorProviderRegistry.ITEM.register(
      (stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack),
      LeatheredBoots.getLeatheredBoots().toArray(LeatheredBootsItem[]::new)
    );
  }
}
