package crystalspider.leatheredboots.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * All leathered boots {@link ArmorMaterial}s.
 */
public enum LeatheredArmorMaterial implements ArmorMaterial {
  LEATHERED_CHAIN(ArmorMaterials.CHAIN),
  LEATHERED_IRON(ArmorMaterials.IRON),
  LEATHERED_GOLD(ArmorMaterials.GOLD),
  LEATHERED_DIAMOND(ArmorMaterials.DIAMOND),
  LEATHERED_NETHERITE(ArmorMaterials.NETHERITE);

  private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
  private final String name;
  private final int durabilityMultiplier;
  private final int[] slotProtections;
  private final int enchantmentValue;
  private final SoundEvent sound;
  private final float toughness;
  private final float knockbackResistance;
  private final Ingredient repairIngredient;

  private LeatheredArmorMaterial(ArmorMaterial armorMaterial) {
    this.name = "leathered_" + armorMaterial.getName();
    this.durabilityMultiplier = armorMaterial.getDurabilityForSlot(EquipmentSlot.FEET) / 13;
    this.slotProtections = new int[]{
      armorMaterial.getDefenseForSlot(EquipmentSlot.FEET),
      armorMaterial.getDefenseForSlot(EquipmentSlot.LEGS),
      armorMaterial.getDefenseForSlot(EquipmentSlot.CHEST),
      armorMaterial.getDefenseForSlot(EquipmentSlot.HEAD)
    };
    this.enchantmentValue = armorMaterial.getEnchantmentValue();
    this.sound = armorMaterial.getEquipSound();
    this.toughness = armorMaterial.getToughness();
    this.knockbackResistance = armorMaterial.getKnockbackResistance();
    this.repairIngredient = armorMaterial.getRepairIngredient();
  }

  public int getDurabilityForSlot(EquipmentSlot equipmentSlot) {
    return HEALTH_PER_SLOT[equipmentSlot.getIndex()] * this.durabilityMultiplier;
  }

  public int getDefenseForSlot(EquipmentSlot equipmentSlot) {
    return this.slotProtections[equipmentSlot.getIndex()];
  }

  public int getEnchantmentValue() {
    return this.enchantmentValue;
  }

  public SoundEvent getEquipSound() {
    return this.sound;
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
