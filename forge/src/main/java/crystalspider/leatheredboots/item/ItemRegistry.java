package crystalspider.leatheredboots.item;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * Registry handler for items and item related stuff.
 */
public class ItemRegistry {
  /**
   * ID for leathered boots creative mod tab.
   */
  private static final String LEATHERED_BOOTS_TAB_ID = "leathered_boots_tab";

  /**
   * {@link CreativeModeTab} {@link DeferredRegister}.
   */
  private static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModLoader.MOD_ID);

  /**
   * {@link RegistryObject} for leathered boots {@link CreativeModeTab}.
   */
  public static final RegistryObject<CreativeModeTab> LEATHERED_BOOTS_TAB = CREATIVE_TABS.register(LEATHERED_BOOTS_TAB_ID, () -> CreativeModeTab.builder()
    .icon(() -> LeatheredBoots.getLeatheredBootsStack(LeatheredArmorMaterial.LEATHERED_NETHERITE))
    .title(Component.translatable("itemGroup." + ModLoader.MOD_ID + "." + LEATHERED_BOOTS_TAB_ID))
    .displayItems((features, output) -> LeatheredBoots.getLeatheredBootsStack().forEach(stack -> output.accept(stack))).build()
  );

  /**
   * Registers all mod items.
   */
  public static void register(IEventBus bus) {
    CREATIVE_TABS.register(bus);
    LeatheredBoots.registerLeatheredBoots(
      LeatheredArmorMaterial.LEATHERED_CHAIN,
      LeatheredArmorMaterial.LEATHERED_IRON,
      LeatheredArmorMaterial.LEATHERED_GOLD,
      LeatheredArmorMaterial.LEATHERED_DIAMOND
    );
    LeatheredBoots.registerLeatheredBoots(true, LeatheredArmorMaterial.LEATHERED_NETHERITE);
  }
}
