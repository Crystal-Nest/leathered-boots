package it.crystalnest.leathered_boots;

import it.crystalnest.leathered_boots.api.LeatheredBoots;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.world.item.DyeableLeatherItem;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class ClientModLoader implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    ColorProviderRegistry.ITEM.register(
      (stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack),
      LeatheredBoots.getLeatheredBoots().toArray(LeatheredBootsItem[]::new)
    );
  }
}
