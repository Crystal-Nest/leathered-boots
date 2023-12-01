package crystalspider.leatheredboots.api;

import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

/**
 * All leathered boots {@link ArmorMaterial}s.
 */
public class LeatheredArmorMaterial implements ArmorMaterial {
  public static final LeatheredArmorMaterial LEATHERED_CHAIN = new LeatheredArmorMaterial(ArmorMaterials.CHAIN);
  public static final LeatheredArmorMaterial LEATHERED_IRON = new LeatheredArmorMaterial(ArmorMaterials.IRON);
  public static final LeatheredArmorMaterial LEATHERED_GOLD = new LeatheredArmorMaterial(ArmorMaterials.GOLD);
  public static final LeatheredArmorMaterial LEATHERED_DIAMOND = new LeatheredArmorMaterial(ArmorMaterials.DIAMOND);
  public static final LeatheredArmorMaterial LEATHERED_NETHERITE = new LeatheredArmorMaterial(ArmorMaterials.NETHERITE);

  private final String name;
  private final ArmorMaterial armorMaterial;

  public LeatheredArmorMaterial(ArmorMaterial armorMaterial) {
    this.name = "leathered_" + armorMaterial.getName();
    this.armorMaterial = armorMaterial;
  }

  @Override
  public int getDurability(Type type) {
    return this.armorMaterial.getDurability(type);
  }

  @Override
  public int getProtection(Type type) {
    return this.armorMaterial.getProtection(type);
  }

  @Override
  public int getEnchantability() {
    return this.armorMaterial.getEnchantability();
  }

  @Override
  public SoundEvent getEquipSound() {
    return this.armorMaterial.getEquipSound();
  }

  @Override
  public Ingredient getRepairIngredient() {
    return this.armorMaterial.getRepairIngredient();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public float getToughness() {
    return this.armorMaterial.getToughness();
  }

  @Override
  public float getKnockbackResistance() {
    return this.armorMaterial.getKnockbackResistance();
  }
}
