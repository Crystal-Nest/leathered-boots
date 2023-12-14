package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

/**
 * Registry handler for items and item related stuff.
 */
public class ItemRegistry {
  /**
   * ID for leathered boots creative mod tab.
   */
  private static final String LEATHERED_BOOTS_TAB_ID = "leathered_boots_tab";

  /**
   * {@link Item} {@link DeferredRegister}.
   */
  private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModLoader.MOD_ID);

  /**
   * {@link CreativeModeTab} {@link DeferredRegister}.
   */
  private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModLoader.MOD_ID);

  /**
   * {@link LeatherUpgradeSmithingTemplateItem} {@link DeferredItem}.
   */
  public static final DeferredItem<LeatherUpgradeSmithingTemplateItem> LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM = ITEMS.register("leather_upgrade_smithing_template", LeatherUpgradeSmithingTemplateItem::new);

  /**
   * {@link DeferredHolder} for leathered boots {@link CreativeModeTab}.
   */
  public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LEATHERED_BOOTS_TAB = CREATIVE_TABS.register(LEATHERED_BOOTS_TAB_ID, () -> CreativeModeTab.builder()
    .icon(() -> LeatheredBoots.getLeatheredBootsStack(LeatheredArmorMaterial.LEATHERED_NETHERITE))
    .title(Component.translatable("itemGroup." + ModLoader.MOD_ID + "." + LEATHERED_BOOTS_TAB_ID))
    .displayItems((features, output) -> {
      output.acceptAll(LeatheredBoots.getLeatheredBootsStack());
      output.accept(LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM.get());
    }).build()
  );

  /**
   * Registers all mod items.
   */
  public static void register(IEventBus bus) {
    ITEMS.register(bus);
    CREATIVE_TABS.register(bus);
    LeatheredBoots.registerLeatheredBoots(
      LeatheredArmorMaterial.LEATHERED_CHAIN,
      LeatheredArmorMaterial.LEATHERED_IRON,
      LeatheredArmorMaterial.LEATHERED_GOLD,
      LeatheredArmorMaterial.LEATHERED_DIAMOND
    );
    LeatheredBoots.registerLeatheredBoots(true, LeatheredArmorMaterial.LEATHERED_NETHERITE);
  }
}
