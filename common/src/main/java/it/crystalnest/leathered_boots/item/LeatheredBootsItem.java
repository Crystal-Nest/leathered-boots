package it.crystalnest.leathered_boots.item;

import net.minecraft.world.item.DyeableArmorItem;
import org.jetbrains.annotations.NotNull;

/**
 * Leathered Boots Item.
 */
public class LeatheredBootsItem extends DyeableArmorItem {
  public LeatheredBootsItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant) {
    super(armorMaterial, Type.BOOTS, isFireResistant ? new Properties().fireResistant() : new Properties());
  }

  @NotNull
  @Override
  public LeatheredArmorMaterial getMaterial() {
    return (LeatheredArmorMaterial) super.getMaterial();
  }
}
