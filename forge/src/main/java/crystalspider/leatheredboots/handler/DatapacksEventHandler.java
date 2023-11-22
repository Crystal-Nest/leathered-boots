package crystalspider.leatheredboots.handler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import crystalspider.leatheredboots.ModLoader;
import crystalspider.leatheredboots.api.LeatheredBoots;
import crystalspider.leatheredboots.packs.DynamicDatapack;
import crystalspider.leatheredboots.packs.TagBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
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

  /**
   * Dynamic datapack to add the {@link ItemTags#FREEZE_IMMUNE_WEARABLES} tag to all Leathered Boots.
   */
  private static final DynamicDatapack DATAPACK = new DynamicDatapack(new ResourceLocation(ModLoader.MOD_ID, "freeze_immune_boots"));

  /**
   * {@link ReloadListener} to refresh the dynamic datapack.
   */
  private static final ReloadListener RELOAD_LISTENER = new ReloadListener(DATAPACK);

  /**
   * Initializes the dynamic datapack related stuff.
   */
  public static final void init() {
    LOGGER.debug("Initializing " + ModLoader.MOD_ID + " Dynamic DataPack " + DATAPACK);
  }

  /**
   * Handles the {@link AddReloadListenerEvent}.
   * <p>
   * Adds the {@link #RELOAD_LISTENER} to reload the dynamic datapack resources.
   * 
   * @param event
   */
  @SubscribeEvent
  public static void handle(AddReloadListenerEvent event) {
    event.addListener(RELOAD_LISTENER);
  }

  /**
   * Handles the {@link ServerStartedEvent}.
   * <p>
   * Forces a reload to refresh resources.
   * For some unknown reasons, despite the dynamic datapack correctly building its resources, unless a subsequent reload is triggered its contents won't be detected by the game.
   * 
   * @param event
   */
  @SubscribeEvent
  public static void handle(ServerStartedEvent event) {
    event.getServer().reloadResources(event.getServer().getPackRepository().getSelectedIds());
  }

  /**
   * Listener for datapack reload events.
   */
  private static class ReloadListener implements PreparableReloadListener {
    /**
     * Dynamic datapack instance.
     */
    private final DynamicDatapack datapack;
    /**
     * Whether the dynamic datapack has already been built.
     */
    private boolean built = false;

    /**
     * @param datapack {@link #datapack}.
     */
    private ReloadListener(DynamicDatapack datapack) {
      this.datapack = datapack;
    }
  
    /**
     * Builds the content of the {@link #datapack dynamic datapack}.
     */
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
      return CompletableFuture.supplyAsync(() -> null, workerExecutor).thenCompose(stage::wait).thenAcceptAsync(o -> {}, mainExecutor);
    }
  }
}
