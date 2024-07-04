package it.crystalnest.leathered_boots.handler;

import it.crystalnest.leathered_boots.Constants;
import it.crystalnest.leathered_boots.api.LeatheredBootsManager;
import it.crystalnest.leathered_boots.item.LeatheredArmorMaterial;
import it.crystalnest.leathered_boots.item.LeatheredBootsItem;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * Event handler for events fired on the {@link Mod.EventBusSubscriber.Bus#MOD Mod Bus}.
 */
@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModEventsHandler {
  private ModEventsHandler() {}

  /**
   * Registers the {@link CauldronInteraction}s for each {@link LeatheredBootsItem}.
   *
   * @param event {@link FMLCommonSetupEvent}.
   */
  @SubscribeEvent
  public static void handle(FMLCommonSetupEvent event) {
    for (LeatheredBootsItem boots : LeatheredBootsManager.getBoots()) {
      CauldronInteraction.WATER.put(boots, CauldronInteraction.DYED_ITEM);
    }
  }

  /**
   * Registers the creative mode tab for leathered boots.
   *
   * @param event {@link CreativeModeTabEvent.Register}.
   */
  @SubscribeEvent
  public static void handle(CreativeModeTabEvent.Register event)  {
    event.registerCreativeModeTab(
      Constants.LEATHERED_BOOTS_TAB_ID,
      builder -> builder
        .icon(() -> LeatheredBootsManager.getBootsStack(Constants.MOD_ID, LeatheredArmorMaterial.LEATHERED_NETHERITE))
        .title(Component.translatable("itemGroup." + Constants.LEATHERED_BOOTS_TAB_ID.toString().replace(":", ".")))
        .displayItems((features, output) -> output.acceptAll(LeatheredBootsManager.getBootsStack()))
    );
  }
}
