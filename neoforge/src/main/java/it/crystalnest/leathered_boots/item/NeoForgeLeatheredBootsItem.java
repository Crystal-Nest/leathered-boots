package it.crystalnest.leathered_boots.item;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * NeoForge Leathered Boots item.
 */
public class NeoForgeLeatheredBootsItem extends LeatheredBootsItem {
  /**
   * @param armorMaterial {@link ArmorMaterial}.
   * @param isFireResistant whether the boots are fire-resistant.
   */
  public NeoForgeLeatheredBootsItem(Holder<ArmorMaterial> armorMaterial, int durabilityFactor, boolean isFireResistant) {
    super(armorMaterial, durabilityFactor, isFireResistant);
  }

  @Override
  public final boolean canWalkOnPowderedSnow(@NotNull ItemStack stack, @NotNull LivingEntity wearer) {
    return true;
  }

  @Override
  public final boolean makesPiglinsNeutral(@NotNull ItemStack stack, @NotNull LivingEntity wearer) {
    return this == ItemRegistry.LEATHERED_GOLDEN_BOOTS.get();
  }
}
