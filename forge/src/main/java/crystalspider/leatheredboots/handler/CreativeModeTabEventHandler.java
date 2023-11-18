package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.item.ItemRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/**
 * {@link CreativeModeTabEvent} handler.
 */
@EventBusSubscriber(modid = ModLoader.MOD_ID, bus = Bus.MOD)
public class CreativeModeTabEventHandler {
  /**
   * Registers the creative mode tab for leathered boots.
   * 
   * @param event
   */
  @SubscribeEvent
  public static void handle(CreativeModeTabEvent.Register event)  {
    event.registerCreativeModeTab(
      new ResourceLocation(ModLoader.MOD_ID, ModLoader.LEATHERED_BOOTS_TAB_ID),
      builder -> builder
        .icon(() -> ItemRegistry.NETHERITE_LEATHERED_BOOTS.get().getDefaultInstance())
        .title(Component.translatable("itemGroup." + ModLoader.MOD_ID + "." + ModLoader.LEATHERED_BOOTS_TAB_ID))
        .displayItems((features, output, hasPermissions) -> {
          output.accept(ItemRegistry.CHAINMAIL_LEATHERED_BOOTS.get().getDefaultInstance());
          output.accept(ItemRegistry.IRON_LEATHERED_BOOTS.get().getDefaultInstance());
          output.accept(ItemRegistry.GOLDEN_LEATHERED_BOOTS.get().getDefaultInstance());
          output.accept(ItemRegistry.DIAMOND_LEATHERED_BOOTS.get().getDefaultInstance());
          output.accept(ItemRegistry.NETHERITE_LEATHERED_BOOTS.get().getDefaultInstance());
        })
    );
  }
}
