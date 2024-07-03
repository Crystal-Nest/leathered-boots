package it.crystalnest.leathered_boots.platform;

import it.crystalnest.leathered_boots.item.ForgeLeatheredBootsItem;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.platform.services.ItemHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

/**
 * Forge item helper.
 */
public final class ForgeItemHelper extends ItemHelper {
  @Override
  public Supplier<LeatheredBootsItem> supplyItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant) {
    return () -> new ForgeLeatheredBootsItem(armorMaterial, isFireResistant);
  }

  @Override
  protected Supplier<CreativeModeTab> buildTab(Supplier<ItemStack> icon, Component title, CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {
    return () -> CreativeModeTab.builder().icon(icon).title(title).displayItems(displayItemsGenerator).build();
  }
}
