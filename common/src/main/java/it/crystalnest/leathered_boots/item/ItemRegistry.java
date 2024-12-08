package it.crystalnest.leathered_boots.item;

import it.crystalnest.cobweb.api.registry.CobwebEntry;
import it.crystalnest.cobweb.api.registry.CobwebRegister;
import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import it.crystalnest.leathered_boots.platform.Services;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.equipment.ArmorMaterials;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

/**
 * Item registry.
 */
@ApiStatus.Internal
public final class ItemRegistry {
  /**
   * {@link CobwebRegister.Items}.
   */
  private static final CobwebRegister.Items ITEMS = CobwebRegistry.ofItems(Constants.MOD_ID);

  /**
   * {@link LeatherUpgradeSmithingTemplateItem}.
   */
  public static final Supplier<LeatherUpgradeSmithingTemplateItem> LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM = ITEMS.registerItem("leather_upgrade_smithing_template", LeatherUpgradeSmithingTemplateItem::new);

  /**
   * {@link LeatheredBootsManager.BootsRegister} for leathered boots.
   */
  private static final LeatheredBootsManager.BootsRegister LEATHERED_BOOTS = LeatheredBootsManager.register(Constants.MOD_ID);

  /**
   * {@link LeatheredBootsItem} for chainmail boots.
   */
  public static final CobwebEntry<LeatheredBootsItem> LEATHERED_CHAIN_BOOTS = LEATHERED_BOOTS.register("chainmail", ArmorMaterials.CHAINMAIL);

  /**
   * {@link LeatheredBootsItem} for iron boots.
   */
  public static final CobwebEntry<LeatheredBootsItem> LEATHERED_IRON_BOOTS = LEATHERED_BOOTS.register("iron", ArmorMaterials.IRON);

  /**
   * {@link LeatheredBootsItem} for golden boots.
   */
  public static final CobwebEntry<LeatheredBootsItem> LEATHERED_GOLDEN_BOOTS = LEATHERED_BOOTS.register("gold", ArmorMaterials.GOLD);

  /**
   * {@link LeatheredBootsItem} for diamond boots.
   */
  public static final CobwebEntry<LeatheredBootsItem> LEATHERED_DIAMOND_BOOTS = LEATHERED_BOOTS.register("diamond", ArmorMaterials.DIAMOND);

  /**
   * {@link LeatheredBootsItem} for netherite boots.
   */
  public static final CobwebEntry<LeatheredBootsItem> LEATHERED_NETHERITE_BOOTS = LEATHERED_BOOTS.register("netherite", ArmorMaterials.NETHERITE, true);

  /**
   * {@link CobwebRegister} for {@link CreativeModeTab}s.
   */
  private static final CobwebRegister<CreativeModeTab> CREATIVE_TABS = CobwebRegistry.ofCreativeModeTabs(Constants.MOD_ID);

  /**
   * Leathered boots tab.<br />
   * Includes all registered leathered boots.
   */
  public static final Supplier<CreativeModeTab> LEATHERED_BOOTS_TAB = CREATIVE_TABS.register(Constants.LEATHERED_BOOTS_TAB_ID, Services.ITEM.supplyTab(
    () -> LEATHERED_NETHERITE_BOOTS.get().getDefaultInstance(),
    Constants.LEATHERED_BOOTS_TAB_ID,
    output -> {
      output.acceptAll(LeatheredBootsManager.getBootsStack());
      output.accept(LEATHER_UPGRADE_SMITHING_TEMPLATE_ITEM.get());
    }
  ));

  private ItemRegistry() {}

  /**
   * Called to load the class and register.
   */
  public static void register() {}
}
