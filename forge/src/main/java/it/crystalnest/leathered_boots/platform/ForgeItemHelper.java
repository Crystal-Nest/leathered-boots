package it.crystalnest.leathered_boots.platform;

import it.crystalnest.leathered_boots.item.ForgeLeatheredBootsItem;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import it.crystalnest.leathered_boots.platform.services.ItemHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Forge item helper.
 */
public final class ForgeItemHelper implements ItemHelper {
  @Override
  public Supplier<LeatheredBootsItem> supplyItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant) {
    return () -> new ForgeLeatheredBootsItem(armorMaterial, isFireResistant);
  }

  @Override
  public CreativeModeTab buildTab(ResourceLocation id, Supplier<ItemStack> icon) {
    return new CreativeModeTab(id.toString().replace(":", ".")) {
      @NotNull
      @Override
      public ItemStack makeIcon() {
        return icon.get();
      }
    };
  }
}
