package crystalspider.leatheredboots.item;

import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Register
 */
public class ModLoader {
  private final String modId;

  public ModLoader(String modId) {
    this.modId = modId;
  }

  public final <R> Register<R> of(DefaultedRegistry<R> registry) {
    return (String key, R value) -> Registry.register(registry, new Identifier(modId, key), value);
  }
}
