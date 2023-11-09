package crystalspider.leatheredboots.api;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegisterProvider {
  private final String modId;

  public RegisterProvider(String modId) {
    this.modId = modId;
  }

  public final <R> Register<R> of(Registry<R> registry) {
    return (key, value) -> Registry.register(registry, new Identifier(modId, key), value);
  }
}
