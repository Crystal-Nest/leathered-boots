package it.crystalnest.leathered_boots.platform;

import com.google.common.base.Suppliers;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.platform.services.ItemHelper;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

/**
 * Fabric item helper.
 */
public final class FabricItemHelper implements ItemHelper {
  @Override
  public Supplier<LeatheredBootsItem> supplyItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant) {
    return Suppliers.memoize(() -> {
      LeatheredBootsItem item = new LeatheredBootsItem(armorMaterial, isFireResistant);
      CauldronInteraction.WATER.put(item, CauldronInteraction.DYED_ITEM);
      return item;
    });
  }

  @Override
  public CreativeModeTab buildTab(ResourceLocation id, Supplier<ItemStack> icon) {
    return FabricItemGroupBuilder.create(id).icon(icon).build();
  }
}
