package it.crystalnest.leathered_boots.item;

import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import org.jetbrains.annotations.NotNull;

/**
 * Leathered Boots Item.
 */
public class LeatheredBootsItem extends ArmorItem {
  /**
   * @param armorMaterial {@link ArmorMaterial}.
   * @param isFireResistant whether the boots are fire-resistant.
   */
  public LeatheredBootsItem(Holder<ArmorMaterial> armorMaterial, int durabilityFactor, boolean isFireResistant) {
    super(armorMaterial, Type.BOOTS, isFireResistant ? getProperties(durabilityFactor).fireResistant() : getProperties(durabilityFactor));
  }

  private static Properties getProperties(int durabilityFactor) {
    return new Properties().durability(Type.BOOTS.getDurability(durabilityFactor));
  }

  @NotNull
  @Override
  public Holder<ArmorMaterial> getMaterial() {
    return super.getMaterial();
  }
}
