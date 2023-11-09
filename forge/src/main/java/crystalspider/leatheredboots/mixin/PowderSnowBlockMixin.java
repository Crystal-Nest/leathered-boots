package crystalspider.leatheredboots.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import crystalspider.leatheredboots.enchantment.EnchantmentRegistry;
import crystalspider.leatheredboots.enchantment.SoftStepEnchantment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.PowderSnowBlock;

/**
 * Injects into {@link PowderSnowBlock} to alter the condition that allows to walk on powdered snow.
 */
@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowBlockMixin {
  /**
   * Inject inside the method {@link PowderSnowBlock#canEntityWalkOnPowderSnow(Entity)}.
   * <p>
   * Extends the condition that allows to walk on powdered snow.
   * 
   * @param entity {@link Entity} the entity to check.
   * @return condition extendend to take into account {@link SoftStepEnchantment}.
   */
  @Inject(method = "canEntityWalkOnPowderSnow", at = @At(value = "RETURN", ordinal = 1))
  private static boolean onCanEntityWalkOnPowderSnow(Entity entity, CallbackInfoReturnable<Boolean> cir) {
    return entity instanceof LivingEntity && ((LivingEntity) entity).getItemBySlot(EquipmentSlot.FEET).getEnchantmentLevel(EnchantmentRegistry.SOFT_STEP.get()) > 0;
  }
}
