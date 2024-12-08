package it.crystalnest.leathered_boots.platform.services;

import it.crystalnest.leathered_boots.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Item related stuff helper.
 */
public abstract class ItemHelper {
  /**
   * Returns a supplier for a {@link CreativeModeTab}.
   *
   * @param icon tab icon.
   * @param id tab id.
   * @param items items to display.
   * @return {@link Supplier} for the {@link CreativeModeTab}.
   */
  public final Supplier<CreativeModeTab> supplyTab(Supplier<ItemStack> icon, String id, Consumer<CreativeModeTab.Output> items) {
    return buildTab(icon, Component.translatable("itemGroup." + Constants.MOD_ID + "." + id), (features, output) -> items.accept(output));
  }

  /**
   * Builds a {@link CreativeModeTab} with the given icon, title, and items to display.
   *
   * @param icon tab icon.
   * @param title tab title.
   * @param displayItemsGenerator tab items generator.
   * @return {@link Supplier} for the {@link CreativeModeTab}.
   */
  protected abstract Supplier<CreativeModeTab> buildTab(Supplier<ItemStack> icon, Component title, CreativeModeTab.DisplayItemsGenerator displayItemsGenerator);
}
