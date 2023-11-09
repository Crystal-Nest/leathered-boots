package crystalspider.leatheredboots.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

/**
 * Mod {@link Enchantment}.
 * <p>
 * Allows to walk on powder snow.
 */
public class SoftStepEnchantment extends Enchantment {
  public SoftStepEnchantment() {
    super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[]{ EquipmentSlot.FEET });
  }
}
