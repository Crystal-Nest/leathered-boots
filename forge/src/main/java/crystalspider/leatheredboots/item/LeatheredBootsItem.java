package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;

/**
 * Leathered Boots Item.
 */
public class LeatheredBootsItem extends DyeableArmorItem {
  public LeatheredBootsItem(ArmorMaterial armorMaterial, boolean isFireResistant) {
    super(armorMaterial, EquipmentSlot.FEET, isFireResistant ? (new Properties()).fireResistant() : new Properties());
  }

  @Override
  public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
    return true;
  }

  @Override
  public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
    return this.material == LeatheredArmorMaterial.LEATHERED_GOLD;
  }
}
