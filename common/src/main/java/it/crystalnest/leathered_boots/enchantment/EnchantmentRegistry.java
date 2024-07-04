package it.crystalnest.leathered_boots.enchantment;

import it.crystalnest.cobweb.api.registry.CobwebRegister;
import it.crystalnest.cobweb.api.registry.CobwebRegistry;
import it.crystalnest.leathered_boots.Constants;
import net.minecraft.core.Registry;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

/**
 * Registry for enchantments.
 */
@ApiStatus.Internal
public final class EnchantmentRegistry {
  /**
   * {@link CobwebRegister} for {@link Enchantment}s.
   */
  private static final CobwebRegister<Enchantment> ENCHANTMENTS = CobwebRegistry.of(Registry.ENCHANTMENT, Constants.MOD_ID);

  /**
   * {@link SoftStepEnchantment}.
   */
  public static final Supplier<SoftStepEnchantment> SOFT_STEP = ENCHANTMENTS.register("soft_step", SoftStepEnchantment::new);

  private EnchantmentRegistry() {}

  /**
   * Called to load the class and register.
   */
  public static void register() {}
}
