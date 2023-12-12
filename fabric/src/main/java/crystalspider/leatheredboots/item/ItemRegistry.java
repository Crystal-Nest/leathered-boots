package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.api.Register;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;

/**
 * Mod {@link Item} and related stuff register.
 */
public class ItemRegistry {
  /**
   * ID for leathered boots creative mod tab.
   */
  private static final String LEATHERED_BOOTS_TAB_ID = "leathered_boots_tab";

  /**
   * {@link Register} for {@link Item}s.
   */
  private static final Register<Item> REGISTER_ITEMS = ModLoader.REGISTER_PROVIDER.of(Registries.ITEM);

  /**
   * {@link Register} for {@link ItemGroup}s.
   */
  private static final Register<ItemGroup> REGISTER_TABS = ModLoader.REGISTER_PROVIDER.of(Registries.ITEM_GROUP);

  /**
   * {@link LeatherUpgradeSmithingTemplateItem}.
   */
  public static final LeatherUpgradeSmithingTemplateItem LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM = new LeatherUpgradeSmithingTemplateItem();

  /**
   * {@link ItemGroup} for {@link LeatheredBootsItem Leathered Boots Items}.
   */
  public static final ItemGroup LEATHERED_BOOTS_GROUP = REGISTER_TABS.apply(LEATHERED_BOOTS_TAB_ID,
    FabricItemGroup
      .builder()
      .icon(() -> LeatheredBoots.getLeatheredBootsStack(LeatheredArmorMaterial.LEATHERED_NETHERITE))
      .displayName(Text.translatable("itemGroup." + ModLoader.MOD_ID + "." + LEATHERED_BOOTS_TAB_ID))
      .entries((features, output) -> {
        output.addAll(LeatheredBoots.getLeatheredBootsStack());
        output.add(LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM);
      })
      .build()
  );

  /**
   * Registers all mod items.
   */
  public static final void register() {
    REGISTER_ITEMS.apply("leather_upgrade_smithing_template", LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM);
    LeatheredBoots.registerLeatheredBoots(
      LeatheredArmorMaterial.LEATHERED_CHAIN,
      LeatheredArmorMaterial.LEATHERED_IRON,
      LeatheredArmorMaterial.LEATHERED_GOLD,
      LeatheredArmorMaterial.LEATHERED_DIAMOND
    );
    LeatheredBoots.registerLeatheredBoots(true, LeatheredArmorMaterial.LEATHERED_NETHERITE);
  }
}
