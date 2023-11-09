package crystalspider.leatheredboots.enchantment;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentRegistry {
  private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, LeatheredBootsLoader.MODID);

  public static RegistryObject<Enchantment> SOFT_STEP = ENCHANTMENTS.register("soft_step", SoftStepEnchantment::new);

  /**
   * Hooks into {@link FMLJavaModLoadingContext} mod event bus.
   * 
   * @param bus {@link IEventBus} 
   */
  public static void register(IEventBus bus) {
    ENCHANTMENTS.register(bus);
  }
}
