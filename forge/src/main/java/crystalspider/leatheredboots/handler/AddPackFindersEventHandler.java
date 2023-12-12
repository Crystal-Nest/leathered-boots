package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.packs.DynamicDatapack;
import crystalspider.leatheredboots.packs.TagBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * {@link AddPackFindersEvent} handler.
 */
@EventBusSubscriber(modid = ModLoader.MOD_ID, bus = Bus.MOD)
public final class AddPackFindersEventHandler {
  /**
   * Register a dynamic datapack for a repository source.
   * 
   * @param event
   */
  @SubscribeEvent
  public static void handle(AddPackFindersEvent event) {
    if (event.getPackType() == PackType.SERVER_DATA) {
      event.addRepositorySource(packConsumer -> packConsumer.accept(
        new DynamicDatapack(
          new ResourceLocation(ModLoader.MOD_ID, "freeze_immune_boots"),
          TagBuilder.of(ItemTags.FREEZE_IMMUNE_WEARABLES, ForgeRegistries.ITEMS).addElements(LeatheredBoots.getLeatheredBoots()),
          TagBuilder.of(ItemTags.TRIMMABLE_ARMOR, ForgeRegistries.ITEMS).addElements(LeatheredBoots.getLeatheredBoots())
        ).create()
      ));
    }
  }
}
