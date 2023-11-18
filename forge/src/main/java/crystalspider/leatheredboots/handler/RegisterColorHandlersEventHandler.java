package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.item.ItemRegistry;
import crystalspider.leatheredboots.item.LeatheredBootsItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/**
 * {@link RegisterColorHandlersEvent} handler.
 */
@EventBusSubscriber(modid = ModLoader.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class RegisterColorHandlersEventHandler {
  /**
   * Registers the {@link ItemColor} for {@link LeatheredBootsItem}.
   * 
   * @param event
   */
  @SubscribeEvent
  public static void handleItem(RegisterColorHandlersEvent.Item event) {
    event.register(
      new ItemColor() {
        public int getColor(ItemStack itemStack, int tintIndex) {
          return tintIndex > 0 ? -1 : ((LeatheredBootsItem) itemStack.getItem()).getColor(itemStack);
        }
      },
      ItemRegistry.CHAINMAIL_LEATHERED_BOOTS.get(),
      ItemRegistry.IRON_LEATHERED_BOOTS.get(),
      ItemRegistry.GOLDEN_LEATHERED_BOOTS.get(),
      ItemRegistry.DIAMOND_LEATHERED_BOOTS.get(),
      ItemRegistry.NETHERITE_LEATHERED_BOOTS.get()
    );
  }
}