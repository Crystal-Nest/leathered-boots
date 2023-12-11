package crystalspider.leatheredboots.enchantment;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.Register;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;

/**
 * Mod {@link Enchantment} register.
 */
public class EnchantmentRegistry {
  private static final Register<Enchantment> ENCHANTMENT_REGISTER = ModLoader.REGISTER_PROVIDER.of(Registries.ENCHANTMENT);

  /**
   * {@link SoftStepEnchantment} {@link Enchantment}.
   */
  public static Enchantment SOFT_STEP = new SoftStepEnchantment();

  /**
   * Registers all mod enchantments.
   */
  public static final void register() {
    ENCHANTMENT_REGISTER.apply("soft_step", SOFT_STEP);
  }
}
