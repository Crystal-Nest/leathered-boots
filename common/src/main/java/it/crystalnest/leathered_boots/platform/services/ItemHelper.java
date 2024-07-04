package it.crystalnest.leathered_boots.platform.services;

import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

/**
 * Item related stuff helper.
 */
public interface ItemHelper {
  /**
   * Returns a supplier for a {@link LeatheredBootsItem}.
   *
   * @param armorMaterial {@link LeatheredArmorMaterial}.
   * @param isFireResistant whether the {@link LeatheredBootsItem} is fire-resistant.
   * @return {@link Supplier} for the {@link LeatheredBootsItem}.
   */
  Supplier<LeatheredBootsItem> supplyItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant);

  /**
   * Builds a {@link CreativeModeTab} with the given ID and icon.
   *
   * @param id tab ID.
   * @param icon tab icon.
   * @return {@link CreativeModeTab}.
   */
  CreativeModeTab buildTab(ResourceLocation id, Supplier<ItemStack> icon);
}
