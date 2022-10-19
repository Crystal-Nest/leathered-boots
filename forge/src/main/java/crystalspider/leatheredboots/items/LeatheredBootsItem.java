package crystalspider.leatheredboots.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;

/**
 * Leathered Boots Item.
 */
public class LeatheredBootsItem extends DyeableArmorItem {
  public LeatheredBootsItem(ArmorMaterial armorMaterial, Properties properties) {
    super(armorMaterial, EquipmentSlot.FEET, properties);
  }

  @Override
  public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
    return true;
  }
}
