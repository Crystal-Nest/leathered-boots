package crystalspider.leatheredboots.packs;

import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.repository.Pack.Info;
import net.minecraft.server.packs.repository.Pack.ResourcesSupplier;

/**
 * Dynamic {@link ResourcesSupplier}.
 */
public class DynamicResourcesSupplier implements ResourcesSupplier {
  /**
   * {@link PackResources} instance.
   */
  private final PackResources instance;

  /**
   * @param instance {@link #instance}.
   */
  DynamicResourcesSupplier(PackResources instance) {
    this.instance = instance;
  }

  @Override
  public PackResources openPrimary(String name) {
    return this.instance;
  }

  @Override
  public PackResources openFull(String name, Info info) {
    return this.instance;
  }
}
