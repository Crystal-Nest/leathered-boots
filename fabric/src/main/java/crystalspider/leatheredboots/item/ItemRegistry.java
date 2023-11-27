package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;

public class ItemRegistry {
  /**
   * {@link ItemGroup} for {@link LeatheredBootsItem Leathered Boots Items}.
   */
  public static final ItemGroup LEATHERED_BOOTS_GROUP = FabricItemGroupBuilder.build(new Identifier(ModLoader.MOD_ID, "leathered_boots_tab"), () -> LeatheredBoots.getLeatheredBootsStack(LeatheredArmorMaterial.LEATHERED_NETHERITE));

  public static final void register() {
    LeatheredBoots.registerLeatheredBoots(
      LeatheredArmorMaterial.LEATHERED_CHAIN,
      LeatheredArmorMaterial.LEATHERED_IRON,
      LeatheredArmorMaterial.LEATHERED_GOLD,
      LeatheredArmorMaterial.LEATHERED_DIAMOND
    );
    LeatheredBoots.registerLeatheredBoots(true, LeatheredArmorMaterial.LEATHERED_NETHERITE);
  }
}
