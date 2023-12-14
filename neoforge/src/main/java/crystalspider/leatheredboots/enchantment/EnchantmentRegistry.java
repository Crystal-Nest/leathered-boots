package crystalspider.leatheredboots.enchantment;

import crystalspider.leatheredboots.ModLoader;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registry handler for enchantments.
 */
public class EnchantmentRegistry {
  /**
   * {@link Enchantment} {@link DeferredRegister}.
   */
  private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, ModLoader.MOD_ID);

  /**
   * {@link DeferredHolder} for the {@link SoftStepEnchantment} {@link Enchantment}.
   */
  public static DeferredHolder<Enchantment, SoftStepEnchantment> SOFT_STEP = ENCHANTMENTS.register("soft_step", SoftStepEnchantment::new);

  /**
   * Hooks into {@link FMLJavaModLoadingContext} mod event bus.
   * 
   * @param bus {@link IEventBus} 
   */
  public static void register(IEventBus bus) {
    ENCHANTMENTS.register(bus);
  }
}
