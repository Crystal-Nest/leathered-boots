package it.crystalnest.leathered_boots.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * Forge Leathered Boots item.
 */
public class ForgeLeatheredBootsItem extends LeatheredBootsItem {
  /**
   * @param armorMaterial {@link LeatheredArmorMaterial}.
   * @param isFireResistant {@link #isFireResistant}.
   */
  public ForgeLeatheredBootsItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant) {
    super(armorMaterial, isFireResistant);
  }

  @Override
  public final boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer) {
    return true;
  }

  @Override
  public final boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer) {
    return getMaterial() == LeatheredArmorMaterial.LEATHERED_GOLD;
  }
}
