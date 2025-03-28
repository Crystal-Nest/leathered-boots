package it.crystalnest.leathered_boots.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

/**
 * Leathered Boots Item.
 */
public class LeatheredBootsItem extends Item {
  /**
   * @param armorMaterial {@link ArmorMaterial}.
   * @param rarity item rarity.
   * @param properties item properties.
   */
  public LeatheredBootsItem(ArmorMaterial armorMaterial, Rarity rarity, Properties properties) {
    this(armorMaterial, properties.rarity(rarity));
  }

  /**
   * @param armorMaterial {@link ArmorMaterial}.
   * @param properties item properties.
   */
  public LeatheredBootsItem(ArmorMaterial armorMaterial, Properties properties) {
    super(properties.humanoidArmor(armorMaterial, ArmorType.BOOTS));
  }
}
