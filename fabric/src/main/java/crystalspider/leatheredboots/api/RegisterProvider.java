package crystalspider.leatheredboots.api;

import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegisterProvider {
  private final String modId;

  public RegisterProvider(String modId) {
    this.modId = modId;
  }

  public final <R> Register<R> of(DefaultedRegistry<R> registry) {
    // return new Object() {
    //   public final <V extends R> V apply(String key, V value) {
    //     return Registry.register(registry, new Identifier(modId, key), value);
    //   }
    // }::apply;
    return (key, value) -> Registry.register(registry, new Identifier(modId, key), value);
  }
}
