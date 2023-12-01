package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import net.minecraft.item.DyeableArmorItem;

/**
 * Leathered Boots Item.
 */
public class LeatheredBootsItem extends DyeableArmorItem {
  public LeatheredBootsItem(LeatheredArmorMaterial armorMaterial, boolean isFireproof) {
    super(armorMaterial, Type.BOOTS, isFireproof ? new Settings().fireproof() : new Settings());
  }

  public LeatheredBootsItem(LeatheredArmorMaterial armorMaterial) {
    this(armorMaterial, false);
  }

  @Override
  public LeatheredArmorMaterial getMaterial() {
    return (LeatheredArmorMaterial) super.getMaterial();
  }
}
