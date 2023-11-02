package crystalspider.leatheredboots.enchantment;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EnchantmentRegistry {
  public static Enchantment SOFT_STEP = register("soft_step", new SoftStepEnchantment());

  private static Enchantment register(String name, Enchantment enchantment) {
    return Registry.register(Registries.ENCHANTMENT, new Identifier(LeatheredBootsLoader.MODID, name), enchantment);
  }
}
