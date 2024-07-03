package it.crystalnest.leathered_boots.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * NeoForge Leathered Boots item.
 */
public class NeoForgeLeatheredBootsItem extends LeatheredBootsItem {
  /**
   * @param armorMaterial {@link LeatheredArmorMaterial}.
   * @param isFireResistant {@link #isFireResistant}.
   */
  public NeoForgeLeatheredBootsItem(LeatheredArmorMaterial armorMaterial, boolean isFireResistant) {
    super(armorMaterial, isFireResistant);
  }

  @Override
  public final boolean canWalkOnPowderedSnow(@NotNull ItemStack stack, @NotNull LivingEntity wearer) {
    return true;
  }

  @Override
  public final boolean makesPiglinsNeutral(@NotNull ItemStack stack, @NotNull LivingEntity wearer) {
    return getMaterial() == LeatheredArmorMaterial.LEATHERED_GOLD;
  }
}
