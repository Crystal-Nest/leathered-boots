package it.crystalnest.leathered_boots.platform.services;

import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface ItemHelper {
  Supplier<CreativeModeTab> buildTab(Supplier<ItemStack> icon, String title, Consumer<CreativeModeTab. Output> items);

  Supplier<LeatheredBootsItem> supplyItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant);
}
