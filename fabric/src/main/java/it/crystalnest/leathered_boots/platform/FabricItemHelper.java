package it.crystalnest.leathered_boots.platform;

import com.google.common.base.Suppliers;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.platform.services.ItemHelper;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Holder;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

/**
 * Fabric item helper.
 */
public final class FabricItemHelper extends ItemHelper {
  @Override
  public Supplier<LeatheredBootsItem> supplyItem(int durabilityFactor, boolean isFireResistant, Holder<ArmorMaterial> armorMaterial) {
    return Suppliers.memoize(() -> {
      LeatheredBootsItem item = new LeatheredBootsItem(armorMaterial, durabilityFactor, isFireResistant);
      CauldronInteraction.WATER.map().put(item, CauldronInteraction.DYED_ITEM);
      return item;
    });
  }

  @Override
  protected Supplier<CreativeModeTab> buildTab(Supplier<ItemStack> icon, Component title, CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {
    return () -> FabricItemGroup.builder().icon(icon).title(title).displayItems(displayItemsGenerator).build();
  }
}
