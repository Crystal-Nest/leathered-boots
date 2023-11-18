package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.item.LeatheredBootsItem;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * {@link FMLCommonSetupEvent} handler.
 */
@EventBusSubscriber(modid = ModLoader.MOD_ID, bus = Bus.MOD)
public class FMLCommonSetupEventHandler {
  /**
   * Registers the {@link CauldronInteraction}s for each {@link LeatheredBootsItem}.
   * 
   * @param event
   */
  @SubscribeEvent
  public static void handle(FMLCommonSetupEvent event) {
    CauldronInteraction.WATER.put(LeatheredBoots.getLeatheredBoots(LeatheredArmorMaterial.LEATHERED_CHAIN), CauldronInteraction.DYED_ITEM);
    CauldronInteraction.WATER.put(LeatheredBoots.getLeatheredBoots(LeatheredArmorMaterial.LEATHERED_IRON), CauldronInteraction.DYED_ITEM);
    CauldronInteraction.WATER.put(LeatheredBoots.getLeatheredBoots(LeatheredArmorMaterial.LEATHERED_GOLD), CauldronInteraction.DYED_ITEM);
    CauldronInteraction.WATER.put(LeatheredBoots.getLeatheredBoots(LeatheredArmorMaterial.LEATHERED_DIAMOND), CauldronInteraction.DYED_ITEM);
    CauldronInteraction.WATER.put(LeatheredBoots.getLeatheredBoots(LeatheredArmorMaterial.LEATHERED_NETHERITE), CauldronInteraction.DYED_ITEM);
  }
}
