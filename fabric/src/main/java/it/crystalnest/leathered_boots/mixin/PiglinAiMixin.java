package it.crystalnest.leathered_boots.mixin;

import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.level.block.PowderSnowBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Injects into {@link PowderSnowBlock} to alter the condition that allows armors to make piglins neutral.
 */
@Mixin(PiglinAi.class)
public abstract class PiglinAiMixin {
  /**
   * Injects at the start of the method {@link PiglinAi#isWearingGold(LivingEntity)}.<br />
   * Adds a check for golden leathered boots.
   *
   * @param entity {@link LivingEntity} to check.
   * @param cir {@link CallbackInfoReturnable}.
   */
  @Inject(method = "isWearingGold", at = @At(value = "HEAD"), cancellable = true)
  private static void onWearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
    if (entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof LeatheredBootsItem leatheredBoots && leatheredBoots.getMaterial() == LeatheredArmorMaterial.LEATHERED_GOLD) {
      cir.setReturnValue(true);
    }
  }
}
