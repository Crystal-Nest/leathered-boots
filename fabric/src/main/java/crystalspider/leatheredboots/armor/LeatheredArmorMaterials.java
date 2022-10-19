package crystalspider.leatheredboots.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

/**
 * All leathered boots {@link ArmorMaterial}s.
 */
public enum LeatheredArmorMaterials implements ArmorMaterial {
  LEATHERED_CHAIN(ArmorMaterials.CHAIN),
  LEATHERED_IRON(ArmorMaterials.IRON),
  LEATHERED_GOLD(ArmorMaterials.GOLD),
  LEATHERED_DIAMOND(ArmorMaterials.DIAMOND),
  LEATHERED_NETHERITE(ArmorMaterials.NETHERITE);

  private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
  private final String name;
  private final int durabilityMultiplier;
  private final int[] protectionAmounts;
  private final int enchantability;
  private final SoundEvent equipSound;
  private final float toughness;
  private final float knockbackResistance;
  private final Ingredient repairIngredient;

  private LeatheredArmorMaterials(ArmorMaterial armorMaterial) {
    this.name = "leathered_" + armorMaterial.getName();
    this.durabilityMultiplier = armorMaterial.getDurability(EquipmentSlot.FEET) / 13;
    this.protectionAmounts = new int[]{
      armorMaterial.getProtectionAmount(EquipmentSlot.FEET),
      armorMaterial.getProtectionAmount(EquipmentSlot.LEGS),
      armorMaterial.getProtectionAmount(EquipmentSlot.CHEST),
      armorMaterial.getProtectionAmount(EquipmentSlot.HEAD)
    };
    this.enchantability = armorMaterial.getEnchantability();
    this.equipSound = armorMaterial.getEquipSound();
    this.toughness = armorMaterial.getToughness();
    this.knockbackResistance = armorMaterial.getKnockbackResistance();
    this.repairIngredient = armorMaterial.getRepairIngredient();
  }

  public int getDurability(EquipmentSlot equipmentSlot) {
    return HEALTH_PER_SLOT[equipmentSlot.getEntitySlotId()] * this.durabilityMultiplier;
  }

  public int getProtectionAmount(EquipmentSlot equipmentSlot) {
    return this.protectionAmounts[equipmentSlot.getEntitySlotId()];
  }

  public int getEnchantability() {
    return this.enchantability;
  }

  public SoundEvent getEquipSound() {
    return this.equipSound;
  }

  public Ingredient getRepairIngredient() {
    return this.repairIngredient;
  }

  public String getName() {
    return this.name;
  }

  public float getToughness() {
    return this.toughness;
  }

  public float getKnockbackResistance() {
    return this.knockbackResistance;
  }
}
