package it.crystalnest.leathered_boots.platform;

import it.crystalnest.leathered_boots.item.ForgeLeatheredBootsItem;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.platform.services.ItemHelper;

import java.util.function.Supplier;

/**
 * Forge item helper.
 */
public final class ForgeItemHelper extends ItemHelper {
  @Override
  public Supplier<LeatheredBootsItem> supplyItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant) {
    return () -> new ForgeLeatheredBootsItem(armorMaterial, isFireResistant);
  }
}
