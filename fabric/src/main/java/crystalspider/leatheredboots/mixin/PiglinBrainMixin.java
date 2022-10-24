package crystalspider.leatheredboots.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;

/**
 * Injects into {@link PowderSnowBlock} to alter the condition that allows armors to make piglins neutral.
 */
@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {
  /**
   * Injects at the start of the method {@link PiglinBrain#wearsGoldArmor(LivingEntity)}.
   * <p>
   * Adds a check for golden leathered boots.
   * 
   * @param entity {@link LivingEntity} to check.
   * @param cir {@link CallbackInfoReturnable}.
   * @return whether the entity is wearing golden leathered boots.
   */
  @Inject(method = "wearsGoldArmor", at = @At(value = "INVOKE"), cancellable = true)
  private static void onWearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
    if (entity.hasStackEquipped(EquipmentSlot.FEET) && entity.getEquippedStack(EquipmentSlot.FEET).isOf(LeatheredBootsLoader.GOLDEN_LEATHERED_BOOTS)) {
      cir.setReturnValue(true);
    }
  }
}
