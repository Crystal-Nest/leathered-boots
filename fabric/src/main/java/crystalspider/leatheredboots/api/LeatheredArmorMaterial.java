package crystalspider.leatheredboots.api;

import net.minecraft.entity.EquipmentSlot;
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
  private final int[] durabilities;
  private final int[] protectionAmounts;
  private final int enchantability;
  private final SoundEvent equipSound;
  private final float toughness;
  private final float knockbackResistance;
  private final Ingredient repairIngredient;

  public LeatheredArmorMaterial(ArmorMaterial armorMaterial) {
    this.name = "leathered_" + armorMaterial.getName();
    this.durabilities = new int[]{
      armorMaterial.getDurability(EquipmentSlot.FEET),
      armorMaterial.getDurability(EquipmentSlot.LEGS),
      armorMaterial.getDurability(EquipmentSlot.CHEST),
      armorMaterial.getDurability(EquipmentSlot.HEAD),
    };
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

  @Override
  public int getDurability(EquipmentSlot equipmentSlot) {
    return this.durabilities[equipmentSlot.getEntitySlotId()];
  }

  @Override
  public int getProtectionAmount(EquipmentSlot equipmentSlot) {
    return this.protectionAmounts[equipmentSlot.getEntitySlotId()];
  }

  @Override
  public int getEnchantability() {
    return this.enchantability;
  }

  @Override
  public SoundEvent getEquipSound() {
    return this.equipSound;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return this.repairIngredient;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public float getToughness() {
    return this.toughness;
  }

  @Override
  public float getKnockbackResistance() {
    return this.knockbackResistance;
  }
}
