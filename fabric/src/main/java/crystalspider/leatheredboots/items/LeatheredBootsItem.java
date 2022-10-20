package crystalspider.leatheredboots.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableArmorItem;

/**
 * Leathered Boots Item.
 */
public class LeatheredBootsItem extends DyeableArmorItem {
  public LeatheredBootsItem(ArmorMaterial armorMaterial, Settings properties) {
    super(armorMaterial, EquipmentSlot.FEET, properties);
  }
}
