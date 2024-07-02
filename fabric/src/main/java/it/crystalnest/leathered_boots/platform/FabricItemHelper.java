package it.crystalnest.leathered_boots.platform;

import com.google.common.base.Suppliers;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.platform.services.ItemHelper;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class FabricItemHelper implements ItemHelper {
  @Override
  public Supplier<CreativeModeTab> buildTab(Supplier<ItemStack> icon, String title, Consumer<CreativeModeTab.Output> items) {
    return () -> FabricItemGroup.builder().icon(icon).title(Component.translatable("itemGroup." + title)).displayItems((features, output) -> items.accept(output)).build();
  }

  @Override
  public Supplier<LeatheredBootsItem> supplyItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant) {
    return Suppliers.memoize(() -> {
      LeatheredBootsItem item = new LeatheredBootsItem(armorMaterial, isFireResistant);
      CauldronInteraction.WATER.map().put(item, CauldronInteraction.DYED_ITEM);
      return item;
    });
  }
}
