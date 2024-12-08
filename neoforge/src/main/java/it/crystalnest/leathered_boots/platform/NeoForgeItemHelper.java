package it.crystalnest.leathered_boots.platform;

import it.crystalnest.leathered_boots.platform.services.ItemHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

/**
 * NeoForge item helper.
 */
public final class NeoForgeItemHelper extends ItemHelper {
  @Override
  protected Supplier<CreativeModeTab> buildTab(Supplier<ItemStack> icon, Component title, CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {
    return () -> CreativeModeTab.builder().icon(icon).title(title).displayItems(displayItemsGenerator).build();
  }
}
