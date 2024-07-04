package it.crystalnest.leathered_boots.platform.services;

import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;

import java.util.function.Supplier;

/**
 * Item related stuff helper.
 */
public abstract class ItemHelper {
  /**
   * Returns a supplier for a {@link LeatheredBootsItem}.
   *
   * @param armorMaterial {@link LeatheredArmorMaterial}.
   * @param isFireResistant whether the {@link LeatheredBootsItem} is fire-resistant.
   * @return {@link Supplier} for the {@link LeatheredBootsItem}.
   */
  public abstract Supplier<LeatheredBootsItem> supplyItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant);
}
