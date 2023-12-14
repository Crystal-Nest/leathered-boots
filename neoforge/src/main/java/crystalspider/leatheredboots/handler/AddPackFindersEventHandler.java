package crystalspider.leatheredboots.handler;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.packs.DynamicDatapack;
import crystalspider.leatheredboots.packs.TagBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.fml.common.Mod.EventBusSubscriber.Bus;
import net.neoforged.neoforge.event.AddPackFindersEvent;

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
          TagBuilder.of(ItemTags.FREEZE_IMMUNE_WEARABLES, BuiltInRegistries.ITEM).addElements(LeatheredBoots.getLeatheredBoots()),
          TagBuilder.of(ItemTags.TRIMMABLE_ARMOR, BuiltInRegistries.ITEM).addElements(LeatheredBoots.getLeatheredBoots())
        ).create()
      ));
    }
  }
}
