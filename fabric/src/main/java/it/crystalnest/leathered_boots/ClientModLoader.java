package it.crystalnest.leathered_boots;

import it.crystalnest.leathered_boots.compat.DetailArmorBar;
import it.crystalnest.leathered_boots.platform.Services;
import net.fabricmc.api.ClientModInitializer;
import org.jetbrains.annotations.ApiStatus;

/**
 * Client mod loader.
 */
@ApiStatus.Internal
public final class ClientModLoader implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    if (Services.PLATFORM.isModLoaded("detailab")) {
      DetailArmorBar.register();
    }
  }
}
