package crystalspider.leatheredboots.items;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

/**
 * Collection of all the {@link ItemGroup}s for this mod.
 */
public class ItemGroups {
  /**
   * {@link ItemGroup} for {@link LeatheredBootsItem Leathered Boots Items}.
   */
  public static final ItemGroup LEATHERED_BOOTS_GROUP = FabricItemGroupBuilder.build(new Identifier(LeatheredBootsLoader.MODID, LeatheredBootsLoader.LEATHERED_BOOTS_ID), () -> LeatheredBootsLoader.NETHERITE_LEATHERED_BOOTS.getDefaultStack());
}
