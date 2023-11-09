package crystalspider.leatheredboots.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

/**
 * Mod {@link Enchantment}.
 * <p>
 * Allows to walk on powder snow.
 */
public class SoftStepEnchantment extends Enchantment {
  public SoftStepEnchantment() {
    super(Rarity.UNCOMMON, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{ EquipmentSlot.FEET });
  }  
}
