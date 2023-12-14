package crystalspider.leatheredboots;

import crystalspider.leatheredboots.enchantment.EnchantmentRegistry;
import crystalspider.leatheredboots.item.ItemRegistry;
import crystalspider.leatheredboots.loot.LootRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;

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
  public static final int PROTOCOL_VERSION = 1_20_2__3_0;
   /**
   * {@link SimpleChannel} instance for compatibility client-server.
   */
  public static final SimpleChannel INSTANCE = ChannelBuilder.named(new ResourceLocation(MOD_ID, "main")).networkProtocolVersion(PROTOCOL_VERSION).simpleChannel();

  public ModLoader() {
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    ItemRegistry.register(bus);
    EnchantmentRegistry.register(bus);
    LootRegistry.register(bus);
  }
}
