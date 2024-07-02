package it.crystalnest.leathered_boots.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ForgeLeatheredBootsItem extends LeatheredBootsItem {
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
