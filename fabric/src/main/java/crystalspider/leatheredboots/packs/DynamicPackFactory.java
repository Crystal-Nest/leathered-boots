package crystalspider.leatheredboots.packs;

import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackProfile.Metadata;
import net.minecraft.resource.ResourcePackProfile.PackFactory;

/**
 * Dynamic {@link PackFactory}.
 */
public class DynamicPackFactory implements PackFactory {
  /**
   * {@link ResourcePack} instance.
   */
  private final ResourcePack instance;

  /**
   * @param instance {@link #instance}.
   */
  DynamicPackFactory(ResourcePack instance) {
    this.instance = instance;
  }

  @Override
  public ResourcePack open(String name) {
    return this.instance;
  }

  @Override
  public ResourcePack openWithOverlays(String name, Metadata metadata) {
    return this.instance;
  }
}
