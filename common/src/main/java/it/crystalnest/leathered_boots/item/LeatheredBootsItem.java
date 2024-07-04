package it.crystalnest.leathered_boots.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DyeableArmorItem;
import org.jetbrains.annotations.NotNull;

/**
 * Leathered Boots Item.
 */
public class LeatheredBootsItem extends DyeableArmorItem {
  /**
   * @param armorMaterial {@link LeatheredArmorMaterial}.
   * @param isFireResistant {@link #isFireResistant}.
   */
  public LeatheredBootsItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant) {
    super(armorMaterial, EquipmentSlot.FEET, isFireResistant ? new Properties().tab(ItemRegistry.LEATHERED_BOOTS_CREATIVE_TAB).fireResistant() : new Properties().tab(ItemRegistry.LEATHERED_BOOTS_CREATIVE_TAB));
  }

  @NotNull
  @Override
  public LeatheredArmorMaterial getMaterial() {
    return (LeatheredArmorMaterial) super.getMaterial();
  }
}
