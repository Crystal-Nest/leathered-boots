package it.crystalnest.leathered_boots.platform;

import com.google.common.base.Suppliers;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.platform.services.ItemHelper;
import net.minecraft.core.cauldron.CauldronInteraction;

import java.util.function.Supplier;

/**
 * Fabric item helper.
 */
public final class FabricItemHelper extends ItemHelper {
  @Override
  public Supplier<LeatheredBootsItem> supplyItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant) {
    return Suppliers.memoize(() -> {
      LeatheredBootsItem item = new LeatheredBootsItem(armorMaterial, isFireResistant);
      CauldronInteraction.WATER.put(item, CauldronInteraction.DYED_ITEM);
      return item;
    });
  }
}
