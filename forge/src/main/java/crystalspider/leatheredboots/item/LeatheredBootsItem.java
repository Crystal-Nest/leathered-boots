package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;

/**
 * Leathered Boots Item.
 */
public class LeatheredBootsItem extends DyeableArmorItem {
  public LeatheredBootsItem(ArmorMaterial armorMaterial, boolean isFireResistant) {
    super(armorMaterial, Type.BOOTS, isFireResistant ? new Properties().fireResistant() : new Properties());
  }

  @Override
  public LeatheredArmorMaterial getMaterial() {
    return (LeatheredArmorMaterial) super.getMaterial();
  }

  @Override
  public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
    return true;
  }

  @Override
  public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
    return getMaterial() == LeatheredArmorMaterial.LEATHERED_GOLD;
  }
}
