package crystalspider.leatheredboots;

import crystalspider.leatheredboots.enchantment.EnchantmentRegistry;
import crystalspider.leatheredboots.item.ItemRegistry;
import crystalspider.leatheredboots.loot.LootRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.network.NetworkRegistry.ChannelBuilder;
import net.neoforged.neoforge.network.simple.SimpleChannel;

/**
 * Leathered Boots mod loader.
 */
@Mod(ModLoader.MOD_ID)
public final class ModLoader {
  /**
   * ID of this mod.
   */
  public static final String MOD_ID = "leatheredboots";

  /**
   * Network channel protocol version.
   */
  public static final String PROTOCOL_VERSION = "1.20.2-3.0";
   /**
   * {@link SimpleChannel} instance for compatibility client-server.
   */
  public static final SimpleChannel INSTANCE = ChannelBuilder.named(new ResourceLocation(MOD_ID, "main")).networkProtocolVersion(() -> PROTOCOL_VERSION).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals).simpleChannel();

  public ModLoader() {
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    ItemRegistry.register(bus);
    EnchantmentRegistry.register(bus);
    LootRegistry.register(bus);
  }
}
