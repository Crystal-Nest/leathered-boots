package it.crystalnest.leathered_boots.platform;

import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.item.NeoForgeLeatheredBootsItem;
import it.crystalnest.leathered_boots.platform.services.ItemHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class NeoForgeItemHelper implements ItemHelper {
  @Override
  public Supplier<CreativeModeTab> buildTab(Supplier<ItemStack> icon, String title, Consumer<CreativeModeTab.Output> items) {
    return () -> CreativeModeTab.builder()
      .icon(icon)
      .title(Component.translatable("itemGroup." + title))
      .displayItems((features, output) -> items.accept(output)).build();
  }

  @Override
  public Supplier<LeatheredBootsItem> supplyItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant) {
    return () -> new NeoForgeLeatheredBootsItem(armorMaterial, isFireResistant);
  }
}
