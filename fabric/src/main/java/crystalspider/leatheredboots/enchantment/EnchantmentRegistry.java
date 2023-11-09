package crystalspider.leatheredboots.enchantment;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import crystalspider.leatheredboots.api.Register;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;

public class EnchantmentRegistry {
  private static final Register<Enchantment> REGISTER = LeatheredBootsLoader.REGISTER_PROVIDER.of(Registries.ENCHANTMENT);

  public static Enchantment SOFT_STEP = new SoftStepEnchantment();

  public static final void register() {
    REGISTER.apply("soft_step", SOFT_STEP);
  }
}
