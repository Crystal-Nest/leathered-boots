package crystalspider.leatheredboots.api;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Concise way for update Minecraft {@link Registries}.
 */
public class RegisterProvider {
  private final String modId;

  public RegisterProvider(String modId) {
    this.modId = modId;
  }

  /**
   * Provides {@link Register} for the specified Minecraft {@link Registries}.
   * 
   * @param <R> type hold by Minecraft {@link Registries}.
   * @param registry the actual Minecraft {@link Registries}.
   * @return {@link Register}
   */
  public final <R> Register<R> of(Registry<R> registry) {
    return (key, value) -> Registry.register(registry, new Identifier(modId, key), value);
  }
}
