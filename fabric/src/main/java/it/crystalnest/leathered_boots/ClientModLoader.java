package it.crystalnest.leathered_boots;

import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import it.crystalnest.leathered_boots.compat.DetailArmorBar;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.platform.Services;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.world.item.DyeableLeatherItem;
import org.jetbrains.annotations.ApiStatus;

/**
 * Client mod loader.
 */
@ApiStatus.Internal
public final class ClientModLoader implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), LeatheredBootsManager.getLeatheredBoots().toArray(LeatheredBootsItem[]::new));
    if (Services.PLATFORM.isModLoaded("detailab")) {
      DetailArmorBar.register();
    }
  }
}
