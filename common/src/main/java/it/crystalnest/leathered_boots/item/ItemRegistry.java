package it.crystalnest.leathered_boots.item;

import it.crystalnest.cobweb.api.registry.CobwebRegister;
import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBoots;
import it.crystalnest.leathered_boots.platform.Services;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

/**
 * Item registry.
 */
@ApiStatus.Internal
public final class ItemRegistry {
  /**
   * {@link CobwebRegister} for {@link Item}s.
   */
  private static final CobwebRegister<Item> ITEMS = CobwebRegistry.ofItems(Constants.MOD_ID);

  /**
   * {@link CobwebRegister} for {@link CreativeModeTab}s.
   */
  private static final CobwebRegister<CreativeModeTab> CREATIVE_TABS = CobwebRegistry.ofCreativeModeTabs(Constants.MOD_ID);

  /**
   * {@link LeatherUpgradeSmithingTemplateItem}.
   */
  public static final Supplier<LeatherUpgradeSmithingTemplateItem> LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM = ITEMS.register("leather_upgrade_smithing_template", LeatherUpgradeSmithingTemplateItem::new);

  /**
   * Leathered boots tab.<br />
   * Includes all registered leathered boots.
   */
  public static final Supplier<CreativeModeTab> LEATHERED_BOOTS_TAB = CREATIVE_TABS.register(Constants.LEATHERED_BOOTS_TAB_ID, Services.ITEM_HELPER.supplyTab(
    () -> LeatheredBoots.getLeatheredBootsStack(Constants.MOD_ID, LeatheredArmorMaterial.LEATHERED_NETHERITE),
    Constants.LEATHERED_BOOTS_TAB_ID,
    output -> {
      output.acceptAll(LeatheredBoots.getLeatheredBootsStack());
      output.accept(LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM.get());
    }
  ));

  private ItemRegistry() {}

  /**
   * Called to load the class and register.
   */
  public static void register() {
    LeatheredBoots.registerLeatheredBoots(
      Constants.MOD_ID,
      LeatheredArmorMaterial.LEATHERED_CHAIN,
      LeatheredArmorMaterial.LEATHERED_IRON,
      LeatheredArmorMaterial.LEATHERED_GOLD,
      LeatheredArmorMaterial.LEATHERED_DIAMOND
    );
    LeatheredBoots.registerLeatheredBoots(Constants.MOD_ID,true, LeatheredArmorMaterial.LEATHERED_NETHERITE);
  }
}
