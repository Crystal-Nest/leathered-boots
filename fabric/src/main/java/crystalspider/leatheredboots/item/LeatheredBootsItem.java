package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.DyeableArmorItem;

/**
 * Leathered Boots Item.
 */
public class LeatheredBootsItem extends DyeableArmorItem {
  public LeatheredBootsItem(LeatheredArmorMaterial armorMaterial, boolean isFireproof) {
    super(armorMaterial, EquipmentSlot.FEET, isFireproof ? new Settings().group(ItemRegistry.LEATHERED_BOOTS_GROUP).fireproof() : new Settings().group(ItemRegistry.LEATHERED_BOOTS_GROUP));
  }

  public LeatheredBootsItem(LeatheredArmorMaterial armorMaterial) {
    this(armorMaterial, false);
  }

  @Override
  public LeatheredArmorMaterial getMaterial() {
    return (LeatheredArmorMaterial) super.getMaterial();
  }
}
