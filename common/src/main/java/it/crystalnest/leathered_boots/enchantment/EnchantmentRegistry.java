package it.crystalnest.leathered_boots.enchantment;

import it.crystalnest.cobweb.api.registry.CobwebRegister;
import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.leathered_boots.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.function.Supplier;

/**
 * Registry handler for enchantments.
 */
public class EnchantmentRegistry {
  private static final CobwebRegister<Enchantment> ENCHANTMENTS = CobwebRegistry.of(Registries.ENCHANTMENT, Constants.MOD_ID);

  public static Supplier<SoftStepEnchantment> SOFT_STEP = ENCHANTMENTS.register("soft_step", SoftStepEnchantment::new);

  public static void register() {}
}
