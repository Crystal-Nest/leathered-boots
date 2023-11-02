package crystalspider.leatheredboots.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableArmorItem;

/**
 * Leathered Boots Item.
 */
public class LeatheredBootsItem extends DyeableArmorItem {
  public LeatheredBootsItem(ArmorMaterial armorMaterial, boolean isFireproof) {
    super(armorMaterial, EquipmentSlot.FEET, isFireproof ? (new Settings()).fireproof() : new Settings());
  }
}
