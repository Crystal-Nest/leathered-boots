package crystalspider.leatheredboots.api;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Concise way for update Minecraft {@link Registry}s.
 */
public class RegisterProvider {
  /**
   * Mod ID used to identify the mod registering objects via the Minecraft {@link Registry}s.
   */
  private final String MOD_ID;

  /**
   * @param modId {@link #MOD_ID}.
   */
  public RegisterProvider(String modId) {
    this.MOD_ID = modId;
  }

  /**
   * Provides {@link Register} for the specified Minecraft {@link Registry}.
   * 
   * @param <R> type hold by Minecraft {@link Registry}.
   * @param registry the actual Minecraft {@link Registry}.
   * @return {@link Register}.
   */
  public final <R> Register<R> of(Registry<R> registry) {
    return (key, value) -> Registry.register(registry, new Identifier(MOD_ID, key), value);
  }
}
