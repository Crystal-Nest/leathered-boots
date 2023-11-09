package crystalspider.leatheredboots.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import crystalspider.leatheredboots.enchantment.EnchantmentRegistry;
import crystalspider.leatheredboots.enchantment.SoftStepEnchantment;
import crystalspider.leatheredboots.item.LeatheredBootsItem;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Injects into {@link PowderSnowBlock} to alter the condition that allows to walk on powdered snow.
 */
@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowBlockMixin {
  /**
   * Redirects the call to {@link ItemStack#isOf(Item)} inside the method {@link PowderSnowBlock#canWalkOnPowderSnow(Entity)}.
   * <p>
   * Changes the condition that allows to walk on powdered snow.
   * 
   * @param caller {@link ItemStack} invoking (owning) the redirected method.
   * @param leatherBoots original {@link Item} that Vanilla checks for.
   * @return condition corrected to take into account {@link LeatheredBootsItem} and {@link SoftStepEnchantment}.
   */
  @Redirect(method = "canWalkOnPowderSnow", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
  private static boolean redirectIsOf(ItemStack caller, Item leatherBoots) {
    return caller.isOf(leatherBoots) || caller.getItem() instanceof LeatheredBootsItem || EnchantmentHelper.getLevel(EnchantmentRegistry.SOFT_STEP, caller) > 0;
  }
}
