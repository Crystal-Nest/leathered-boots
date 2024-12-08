package it.crystalnest.leathered_boots.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

/**
 * Leathered Boots Item.
 */
public class LeatheredBootsItem extends ArmorItem {
  /**
   * @param armorMaterial {@link ArmorMaterial}.
   * @param properties item properties.
   */
  public LeatheredBootsItem(ArmorMaterial armorMaterial, Properties properties) {
    super(armorMaterial, ArmorType.BOOTS, properties);
  }
}
