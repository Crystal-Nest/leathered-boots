package it.crystalnest.leathered_boots.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * Injects into {@link PowderSnowBlock} to alter the condition that allows to walk on powdered snow.
 */
@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowBlockMixin {
  /**
   * Inject inside the method {@link PowderSnowBlock#canEntityWalkOnPowderSnow(Entity)}.<br />
   * Extends the condition that allows to walk on powdered snow.
   *
   * @param original original condition value.
   * @param entity {@link Entity} the entity to check.
   * @return condition extended to take into account {@link LeatheredBootsItem} and Soft Step enchantment.
   */
  @ModifyReturnValue(method = "canEntityWalkOnPowderSnow", at = @At(value = "RETURN", ordinal = 1))
  private static boolean modifyCanEntityWalkOnPowderSnow(boolean original, Entity entity) {
    return original || entity instanceof LivingEntity livingEntity && (
      livingEntity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof LeatheredBootsItem ||
      EnchantmentHelper.getItemEnchantmentLevel(
        entity.level().holderLookup(Registries.ENCHANTMENT).getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "soft_step"))),
        livingEntity.getItemBySlot(EquipmentSlot.FEET)
      ) > 0
    );
  }
}
