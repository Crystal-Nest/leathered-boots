package crystalspider.leatheredboots.handlers;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import crystalspider.leatheredboots.items.LeatheredBootsItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/**
 * {@link ColorHandlerEvent} handler.
 */
@EventBusSubscriber(modid = LeatheredBootsLoader.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ColorHandlerEventHandler {
  /**
   * Registers the {@link ItemColor} for {@link LeatheredBootsItem}.
   * 
   * @param event
   */
  @SubscribeEvent
  public static void handleItem(ColorHandlerEvent.Item event) {
    event.getItemColors().register(
      new ItemColor() {
        public int getColor(ItemStack itemStack, int tintIndex) {
          return tintIndex > 0 ? -1 : ((LeatheredBootsItem) itemStack.getItem()).getColor(itemStack);
        }
      },
      LeatheredBootsLoader.CHAINMAIL_LEATHERED_BOOTS.get(),
      LeatheredBootsLoader.IRON_LEATHERED_BOOTS.get(),
      LeatheredBootsLoader.GOLDEN_LEATHERED_BOOTS.get(),
      LeatheredBootsLoader.DIAMOND_LEATHERED_BOOTS.get(),
      LeatheredBootsLoader.NETHERITE_LEATHERED_BOOTS.get()
    );
  }
}