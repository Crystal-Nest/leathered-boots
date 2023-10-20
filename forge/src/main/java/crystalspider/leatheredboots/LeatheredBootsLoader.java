package crystalspider.leatheredboots;

import crystalspider.leatheredboots.enchantment.EnchantmentRegistry;
import crystalspider.leatheredboots.item.ItemRegistry;
import crystalspider.leatheredboots.loot.LootModifierRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * Leathered Boots mod loader.
 */
@Mod(LeatheredBootsLoader.MODID)
public class LeatheredBootsLoader {
  /**
   * ID of this mod.
   */
  public static final String MODID = "leatheredboots";

  /**
   * Base ID for leathered boots.
   */
  public static final String LEATHERED_BOOTS_ID = "leathered_boots";

  /**
   * ID for leathered boots creative mod tab.
   */
  public static final String LEATHERED_BOOTS_TAB_ID = LEATHERED_BOOTS_ID + "_tab";

  /**
   * Network channel protocol version.
   */
  public static final String PROTOCOL_VERSION = "1.19-2.0";
  /**
   * {@link SimpleChannel} instance for compatibility client-server.
   */
  public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(MODID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

  public LeatheredBootsLoader() {
    IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    ItemRegistry.register(modEventBus);
    EnchantmentRegistry.register(modEventBus);
    LootModifierRegistry.register(modEventBus);
  }
}
