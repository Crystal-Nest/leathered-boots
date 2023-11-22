package crystalspider.leatheredboots.handler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.packs.DynamicDataPack;
import crystalspider.leatheredboots.packs.TagBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/**
 * Handles datapack related events.
 */
@EventBusSubscriber(modid = ModLoader.MOD_ID, bus = Bus.FORGE)
public final class DatapacksEventHandler {
  /**
   * Logger.
   */
  private static final Logger LOGGER = LogManager.getLogger();

  private static final DynamicDataPack DATAPACK = new DynamicDataPack(new ResourceLocation(ModLoader.MOD_ID, "freeze_immune_boots"));

  private static final ReloadListener RELOAD_LISTENER = new ReloadListener(DATAPACK);

  public static final void init() {
    // TODO
  }

  /**
   * Handles the {@link AddReloadListenerEvent}.
   * 
   * @param event
   */
  @SubscribeEvent
  public static void handle(AddReloadListenerEvent event) {
    event.addListener(RELOAD_LISTENER);
  }

  private static class ReloadListener implements PreparableReloadListener {
    private final DynamicDataPack datapack;
    private boolean built;

    private ReloadListener(DynamicDataPack datapack) {
      this.datapack = datapack;
    }
  
    private void build() {
      this.datapack.buildItemTag(TagBuilder.of(ItemTags.FREEZE_IMMUNE_WEARABLES).addElements(LeatheredBoots.getLeatheredBoots()));
    }

    @Override
    public CompletableFuture<Void> reload(PreparationBarrier stage, ResourceManager manager, ProfilerFiller workerProfiler, ProfilerFiller mainProfiler, Executor workerExecutor, Executor mainExecutor) {
      if (!this.built) {
        try {
          this.build();
          this.built = true;
        } catch (Exception e) {
          LOGGER.error("The following error was thrown while attempting to build the " + this.datapack + " dynamic datapack from leatheredboots:", e);
        }
      }
      return CompletableFuture.supplyAsync(() -> null, workerExecutor).thenCompose(stage::wait).thenAcceptAsync(noResult -> {}, mainExecutor);
    }
  }
}
