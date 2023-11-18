package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.item.ItemRegistry;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * {@link VillagerTradesEvent} handler.
 */
@EventBusSubscriber(modid = ModLoader.MOD_ID)
public class VillagerTradesEventHandler {
  /**
   * Adds trades for some leathered boots.
   * 
   * @param event
   */
  @SubscribeEvent
  public static void handle(VillagerTradesEvent event) {
    if (event.getType() == VillagerProfession.LEATHERWORKER) {
      event.getTrades().get(3).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 4), ItemRegistry.CHAINMAIL_LEATHERED_BOOTS.get().getDefaultInstance(), 5, 6, 0.02F));
      event.getTrades().get(4).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 9), ItemRegistry.IRON_LEATHERED_BOOTS.get().getDefaultInstance(), 3, 10, 0.02F));
      event.getTrades().get(5).add((trader, rand) -> new MerchantOffer(new ItemStack(Items.EMERALD, 13), ItemRegistry.DIAMOND_LEATHERED_BOOTS.get().getDefaultInstance(), 1, 30, 0.02F));
    }
  }
}
