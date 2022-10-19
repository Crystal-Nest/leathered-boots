package crystalspider.leatheredboots.handlers;

import crystalspider.leatheredboots.LeatheredBootsLoader;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * {@link VillagerTradesEvent} handler.
 */
public class VillagerTradesEventHandler {
  /**
   * Adds trades for some leathered boots.
   * 
   * @param event
   */
  @SubscribeEvent
  public void handle(VillagerTradesEvent event) {
    if (event.getType() == VillagerProfession.LEATHERWORKER) {
      event.getTrades().get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 4), LeatheredBootsLoader.CHAINMAIL_LEATHERED_BOOTS.get().getDefaultInstance(), 5, 6, 0.02F));
      event.getTrades().get(4).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 9), LeatheredBootsLoader.IRON_LEATHERED_BOOTS.get().getDefaultInstance(), 3, 10, 0.02F));
      event.getTrades().get(5).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 13), LeatheredBootsLoader.DIAMOND_LEATHERED_BOOTS.get().getDefaultInstance(), 1, 30, 0.02F));
    }
  }
}
