package crystalspider.leatheredboots.api;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * All leathered boots {@link ArmorMaterial}s.
 */
public class LeatheredArmorMaterial implements ArmorMaterial {
  /**
   * {@link LeatheredArmorMaterial} for Vanilla {@link ArmorMaterials#CHAIN}.
   */
  public static final LeatheredArmorMaterial LEATHERED_CHAIN = new LeatheredArmorMaterial(ArmorMaterials.CHAIN);
  /**
   * {@link LeatheredArmorMaterial} for Vanilla {@link ArmorMaterials#IRON}.
   */
  public static final LeatheredArmorMaterial LEATHERED_IRON = new LeatheredArmorMaterial(ArmorMaterials.IRON);
  /**
   * {@link LeatheredArmorMaterial} for Vanilla {@link ArmorMaterials#GOLD}.
   */
  public static final LeatheredArmorMaterial LEATHERED_GOLD = new LeatheredArmorMaterial(ArmorMaterials.GOLD);
  /**
   * {@link LeatheredArmorMaterial} for Vanilla {@link ArmorMaterials#DIAMOND}.
   */
  public static final LeatheredArmorMaterial LEATHERED_DIAMOND = new LeatheredArmorMaterial(ArmorMaterials.DIAMOND);
   /**
   * {@link LeatheredArmorMaterial} for Vanilla {@link ArmorMaterials#NETHERITE}.
   */
  public static final LeatheredArmorMaterial LEATHERED_NETHERITE = new LeatheredArmorMaterial(ArmorMaterials.NETHERITE);

  /**
   * Name of this {@link ArmorMaterial}.
   * <p>
   * Always equal to {@code "leathered_" + baseArmorMaterialName}.
   */
  private final String name;
  /**
   * Base {@link ArmorMaterial}.
   */
  private final ArmorMaterial armorMaterial;
  
  /**
   * @param armorMaterial {@link #armorMaterial}.
   */
  public LeatheredArmorMaterial(ArmorMaterial armorMaterial) {
    this.name = "leathered_" + armorMaterial.getName();
    this.armorMaterial = armorMaterial;
  }

  @Override
  public int getDurabilityForType(Type type) {
    return this.armorMaterial.getDurabilityForType(type);
  }

  @Override
  public int getDefenseForType(Type type) {
    return this.armorMaterial.getDefenseForType(type);
  }

  @Override
  public int getEnchantmentValue() {
    return this.armorMaterial.getEnchantmentValue();
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
