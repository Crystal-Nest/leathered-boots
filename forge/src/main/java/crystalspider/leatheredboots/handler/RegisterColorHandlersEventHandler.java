package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;
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
      LeatheredBoots.getLeatheredBoots(LeatheredArmorMaterial.LEATHERED_CHAIN),
      LeatheredBoots.getLeatheredBoots(LeatheredArmorMaterial.LEATHERED_IRON),
      LeatheredBoots.getLeatheredBoots(LeatheredArmorMaterial.LEATHERED_GOLD),
      LeatheredBoots.getLeatheredBoots(LeatheredArmorMaterial.LEATHERED_DIAMOND),
      LeatheredBoots.getLeatheredBoots(LeatheredArmorMaterial.LEATHERED_NETHERITE)
    );
  }
}