package crystalspider.leatheredboots.api;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;

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
  private final int[] slotDurabilities;
  private final int[] slotProtections;
  private final int enchantmentValue;
  private final SoundEvent sound;
  private final float toughness;
  private final float knockbackResistance;
  private final Ingredient repairIngredient;

  public LeatheredArmorMaterial(ArmorMaterial armorMaterial) {
    this.name = "leathered_" + armorMaterial.getName();
    this.slotDurabilities = new int[]{
      armorMaterial.getDurabilityForSlot(EquipmentSlot.FEET),
      armorMaterial.getDurabilityForSlot(EquipmentSlot.LEGS),
      armorMaterial.getDurabilityForSlot(EquipmentSlot.CHEST),
      armorMaterial.getDurabilityForSlot(EquipmentSlot.HEAD)
    };
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

  @Override
  public int getDurabilityForSlot(EquipmentSlot equipmentSlot) {
    return this.slotDurabilities[equipmentSlot.getIndex()];
  }

  @Override
  public int getDefenseForSlot(EquipmentSlot equipmentSlot) {
    return this.slotProtections[equipmentSlot.getIndex()];
  }

  @Override
  public int getEnchantmentValue() {
    return this.enchantmentValue;
  }

  @Override
  public SoundEvent getEquipSound() {
    return this.sound;
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
