package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredArmorMaterial;
import crystalspider.leatheredboots.api.LeatheredBoots;
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
        .icon(() -> LeatheredBoots.getLeatheredBootsStack(LeatheredArmorMaterial.LEATHERED_NETHERITE))
        .title(Component.translatable("itemGroup." + ModLoader.MOD_ID + "." + ModLoader.LEATHERED_BOOTS_TAB_ID))
        .displayItems((features, output, hasPermissions) -> LeatheredBoots.getLeatheredBootsStack().forEach(stack -> output.accept(stack)))
    );
  }
}
